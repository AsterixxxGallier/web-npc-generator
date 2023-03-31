import kotlinx.html.*

@DslMarker
annotation class StatBlockTagMarker

@DslMarker
annotation class AttributeOrDetailMarker

@DslMarker
annotation class ScoreMarker

typealias Block = FlowOrPhrasingContent.() -> Unit
typealias SectionBlock = Section.() -> Unit

class StatBlock {
    private var title: Block = {}
    private var subtitle: Block = {}
    private var attributes: MutableList<Pair<Block, Block>> = mutableListOf()
    private var stats: MutableList<Pair<Block, Block>> = mutableListOf()
    private var details: MutableList<Pair<Block, Block>> = mutableListOf()
    private var features: SectionBlock = {}
    private var sections: MutableList<Pair<Block, SectionBlock>> = mutableListOf()

    operator fun String.unaryPlus(): Block =
        { +this@unaryPlus }

    @StatBlockTagMarker
    fun title(block: Block) {
        title = block
    }

    @StatBlockTagMarker
    fun subtitle(block: Block) {
        subtitle = block
    }

    @AttributeOrDetailMarker
    fun attribute(name: Block, value: Block) {
        attributes.add(name to value)
    }

    @ScoreMarker
    fun score(name: Block, value: Block) {
        stats.add(name to value)
    }

    @AttributeOrDetailMarker
    fun detail(name: Block, value: Block) {
        details.add(name to value)
    }

    @StatBlockTagMarker
    fun features(block: SectionBlock) {
        features = block
    }

    @StatBlockTagMarker
    fun section(title: Block, block: SectionBlock) {
        sections.add(title to block)
    }

    fun TagConsumer<*>.apply() {
        div("block monster frame") {
            h2 {
                this@StatBlock.title.invoke(this)
            }
            p {
                em {
                    subtitle()
                }
            }
            hr()
            dl {
                this@StatBlock.attributes.forEach { (name, value) ->
                    dt {
                        strong {
                            name()
                        }
                    }
                    dd {
                        value()
                    }
                }
            }
            hr()
            table {
                thead {
                    tr {
                        stats.forEach { (name, _) ->
                            th {
                                attributes["align"] = "center"
                                name()
                            }
                        }
                    }
                }
                tbody {
                    tr {
                        stats.forEach { (_, value) ->
                            td {
                                attributes["align"] = "center"
                                value()
                            }
                        }
                    }
                }
            }
            hr()
            dl {
                details.forEach { (name, value) ->
                    dt {
                        strong {
                            name()
                        }
                    }
                    dd {
                        value()
                    }
                }
            }
            hr()
            val entries = Section().apply(features).entries
            entries
                .flatMap { listOf(it, { this@div.div("blank") }) }
                .dropLast(1)
                .forEach { it() }
            if (entries.isNotEmpty()) {
                div("blank")
            }
            sections.forEach { (title, sectionBlock) ->
                h3 {
                    title()
                }
                Section().apply(sectionBlock).entries
                    .flatMap { listOf(it, { this@div.div("blank") }) }
                    .dropLast(1)
                    .forEach { it() }
            }
        }
    }
}

class Section {
    val entries: MutableList<Block> = mutableListOf()

    @StatBlockTagMarker
    fun entry(title: Block, value: Block) {
        entries.add {
            this as FlowContent
            this@add.p {
                em {
                    strong {
                        title()
                        +"."
                    }
                }
                +" "
                value()
            }
        }
    }
}

inline fun <T, C : TagConsumer<T>> C.statBlock(crossinline block: StatBlock.() -> Unit = {}) {
    StatBlock().apply(block).apply { apply() }
}
