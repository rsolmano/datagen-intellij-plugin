package com.solmano.datagen.actions

import kotlin.random.Random.Default.nextInt

class GenerateLoremWordAction : BaseGenerateAction() {
    
    private val loremWords = listOf(
        "lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit",
        "sed", "do", "eiusmod", "tempor", "incididunt", "ut", "labore", "et",
        "dolore", "magna", "aliqua", "enim", "ad", "minim", "veniam",
        "quis", "nostrud", "exercitation", "ullamco", "laboris", "nisi", "aliquip",
        "ex", "ea", "commodo", "consequat", "duis", "aute", "irure", "in",
        "reprehenderit", "voluptate", "velit", "esse", "cillum", "fugiat", "nulla",
        "pariatur", "excepteur", "sint", "occaecat", "cupidatat", "non", "proident",
        "sunt", "culpa", "qui", "officia", "deserunt", "mollit", "anim", "id",
        "est", "laborum", "at", "vero", "eos", "accusamus", "accusantium",
        "doloremque", "laudantium", "totam", "rem", "aperiam", "eaque", "ipsa",
        "quae", "ab", "illo", "inventore", "veritatis", "et", "quasi", "architecto",
        "beatae", "vitae", "dicta", "sunt", "explicabo", "nemo", "ipsam", "voluptatem",
        "quia", "voluptas", "aspernatur", "aut", "odit", "fugit", "sed", "quia",
        "consequuntur", "magni", "dolores", "ratione", "voluptatem", "sequi", "nesciunt",
        "neque", "porro", "quisquam", "est", "qui", "dolorem", "ipsum", "quia",
        "dolor", "sit", "amet", "consectetur", "adipisci", "velit", "sed", "quia",
        "numquam", "eius", "modi", "tempora", "incidunt", "ut", "labore", "et",
        "dolore", "magnam", "aliquam", "quaerat", "voluptatem", "ut", "enim", "ad",
        "minima", "veniam", "quis", "nostrum", "exercitationem", "ullam", "corporis",
        "suscipit", "laboriosam", "nisi", "ut", "aliquid", "ex", "ea", "commodi",
        "consequatur", "quis", "autem", "vel", "eum", "iure", "reprehenderit", "qui",
        "in", "ea", "voluptate", "velit", "esse", "quam", "nihil", "molestiae",
        "consequatur", "vel", "illum", "qui", "dolorem", "eum", "fugiat", "quo",
        "voluptas", "nulla", "pariatur"
    )
    
    override fun generate(): String = loremWords.random()
}

class GenerateLoremSentenceAction : BaseGenerateAction() {
    
    private val loremWords = listOf(
        "lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit",
        "sed", "do", "eiusmod", "tempor", "incididunt", "ut", "labore", "et",
        "dolore", "magna", "aliqua", "enim", "ad", "minim", "veniam",
        "quis", "nostrud", "exercitation", "ullamco", "laboris", "nisi", "aliquip",
        "ex", "ea", "commodo", "consequat", "duis", "aute", "irure", "in",
        "reprehenderit", "voluptate", "velit", "esse", "cillum", "fugiat", "nulla",
        "pariatur", "excepteur", "sint", "occaecat", "cupidatat", "non", "proident",
        "sunt", "culpa", "qui", "officia", "deserunt", "mollit", "anim", "id",
        "est", "laborum", "at", "vero", "eos", "accusamus", "accusantium",
        "doloremque", "laudantium", "totam", "rem", "aperiam", "eaque", "ipsa",
        "quae", "ab", "illo", "inventore", "veritatis", "et", "quasi", "architecto",
        "beatae", "vitae", "dicta", "sunt", "explicabo", "nemo", "ipsam", "voluptatem",
        "quia", "voluptas", "aspernatur", "aut", "odit", "fugit", "sed", "quia",
        "consequuntur", "magni", "dolores", "ratione", "voluptatem", "sequi", "nesciunt",
        "neque", "porro", "quisquam", "est", "qui", "dolorem", "ipsum", "quia",
        "dolor", "sit", "amet", "consectetur", "adipisci", "velit", "sed", "quia",
        "numquam", "eius", "modi", "tempora", "incidunt", "ut", "labore", "et",
        "dolore", "magnam", "aliquam", "quaerat", "voluptatem", "ut", "enim", "ad",
        "minima", "veniam", "quis", "nostrum", "exercitationem", "ullam", "corporis",
        "suscipit", "laboriosam", "nisi", "ut", "aliquid", "ex", "ea", "commodi",
        "consequatur", "quis", "autem", "vel", "eum", "iure", "reprehenderit", "qui",
        "in", "ea", "voluptate", "velit", "esse", "quam", "nihil", "molestiae",
        "consequatur", "vel", "illum", "qui", "dolorem", "eum", "fugiat", "quo",
        "voluptas", "nulla", "pariatur"
    )
    
    override fun generate(): String {
        val wordCount = nextInt(8, 16) // 8-15 words per sentence
        val words = mutableListOf<String>()
        
        // Always start with "Lorem ipsum" for the first sentence
        if (Math.random() < 0.3) {
            words.add("Lorem")
            words.add("ipsum")
            repeat(wordCount - 2) {
                words.add(loremWords.random())
            }
        } else {
            repeat(wordCount) {
                words.add(loremWords.random())
            }
        }
        
        // Capitalize first word and add period
        val sentence = words.joinToString(" ")
        return sentence.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() } + "."
    }
}

class GenerateLoremShortParagraphAction : BaseGenerateAction() {
    
    private val loremWords = listOf(
        "lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit",
        "sed", "do", "eiusmod", "tempor", "incididunt", "ut", "labore", "et",
        "dolore", "magna", "aliqua", "enim", "ad", "minim", "veniam",
        "quis", "nostrud", "exercitation", "ullamco", "laboris", "nisi", "aliquip",
        "ex", "ea", "commodo", "consequat", "duis", "aute", "irure", "in",
        "reprehenderit", "voluptate", "velit", "esse", "cillum", "fugiat", "nulla",
        "pariatur", "excepteur", "sint", "occaecat", "cupidatat", "non", "proident",
        "sunt", "culpa", "qui", "officia", "deserunt", "mollit", "anim", "id",
        "est", "laborum", "at", "vero", "eos", "accusamus", "accusantium",
        "doloremque", "laudantium", "totam", "rem", "aperiam", "eaque", "ipsa",
        "quae", "ab", "illo", "inventore", "veritatis", "et", "quasi", "architecto",
        "beatae", "vitae", "dicta", "sunt", "explicabo", "nemo", "ipsam", "voluptatem",
        "quia", "voluptas", "aspernatur", "aut", "odit", "fugit", "sed", "quia",
        "consequuntur", "magni", "dolores", "ratione", "voluptatem", "sequi", "nesciunt",
        "neque", "porro", "quisquam", "est", "qui", "dolorem", "ipsum", "quia",
        "dolor", "sit", "amet", "consectetur", "adipisci", "velit", "sed", "quia",
        "numquam", "eius", "modi", "tempora", "incidunt", "ut", "labore", "et",
        "dolore", "magnam", "aliquam", "quaerat", "voluptatem", "ut", "enim", "ad",
        "minima", "veniam", "quis", "nostrum", "exercitationem", "ullam", "corporis",
        "suscipit", "laboriosam", "nisi", "ut", "aliquid", "ex", "ea", "commodi",
        "consequatur", "quis", "autem", "vel", "eum", "iure", "reprehenderit", "qui",
        "in", "ea", "voluptate", "velit", "esse", "quam", "nihil", "molestiae",
        "consequatur", "vel", "illum", "qui", "dolorem", "eum", "fugiat", "quo",
        "voluptas", "nulla", "pariatur"
    )
    
    override fun generate(): String {
        val sentenceCount = nextInt(3, 6) // 3-5 sentences
        val sentences = mutableListOf<String>()
        
        repeat(sentenceCount) {
            val wordCount = nextInt(8, 16) // 8-15 words per sentence
            val words = mutableListOf<String>()
            
            // First sentence can start with "Lorem ipsum"
            if (it == 0 && Math.random() < 0.7) {
                words.add("Lorem")
                words.add("ipsum")
                repeat(wordCount - 2) {
                    words.add(loremWords.random())
                }
            } else {
                repeat(wordCount) {
                    words.add(loremWords.random())
                }
            }
            
            // Capitalize first word and add period
            val sentence = words.joinToString(" ")
            sentences.add(sentence.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() } + ".")
        }
        
        return sentences.joinToString(" ")
    }
}

class GenerateLoremLongParagraphAction : BaseGenerateAction() {
    
    private val loremWords = listOf(
        "lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit",
        "sed", "do", "eiusmod", "tempor", "incididunt", "ut", "labore", "et",
        "dolore", "magna", "aliqua", "enim", "ad", "minim", "veniam",
        "quis", "nostrud", "exercitation", "ullamco", "laboris", "nisi", "aliquip",
        "ex", "ea", "commodo", "consequat", "duis", "aute", "irure", "in",
        "reprehenderit", "voluptate", "velit", "esse", "cillum", "fugiat", "nulla",
        "pariatur", "excepteur", "sint", "occaecat", "cupidatat", "non", "proident",
        "sunt", "culpa", "qui", "officia", "deserunt", "mollit", "anim", "id",
        "est", "laborum", "at", "vero", "eos", "accusamus", "accusantium",
        "doloremque", "laudantium", "totam", "rem", "aperiam", "eaque", "ipsa",
        "quae", "ab", "illo", "inventore", "veritatis", "et", "quasi", "architecto",
        "beatae", "vitae", "dicta", "sunt", "explicabo", "nemo", "ipsam", "voluptatem",
        "quia", "voluptas", "aspernatur", "aut", "odit", "fugit", "sed", "quia",
        "consequuntur", "magni", "dolores", "ratione", "voluptatem", "sequi", "nesciunt",
        "neque", "porro", "quisquam", "est", "qui", "dolorem", "ipsum", "quia",
        "dolor", "sit", "amet", "consectetur", "adipisci", "velit", "sed", "quia",
        "numquam", "eius", "modi", "tempora", "incidunt", "ut", "labore", "et",
        "dolore", "magnam", "aliquam", "quaerat", "voluptatem", "ut", "enim", "ad",
        "minima", "veniam", "quis", "nostrum", "exercitationem", "ullam", "corporis",
        "suscipit", "laboriosam", "nisi", "ut", "aliquid", "ex", "ea", "commodi",
        "consequatur", "quis", "autem", "vel", "eum", "iure", "reprehenderit", "qui",
        "in", "ea", "voluptate", "velit", "esse", "quam", "nihil", "molestiae",
        "consequatur", "vel", "illum", "qui", "dolorem", "eum", "fugiat", "quo",
        "voluptas", "nulla", "pariatur"
    )
    
    override fun generate(): String {
        val sentenceCount = nextInt(8, 12) // 8-11 sentences
        val sentences = mutableListOf<String>()
        
        repeat(sentenceCount) {
            val wordCount = nextInt(8, 16) // 8-15 words per sentence
            val words = mutableListOf<String>()
            
            // First sentence can start with "Lorem ipsum"
            if (it == 0 && Math.random() < 0.7) {
                words.add("Lorem")
                words.add("ipsum")
                repeat(wordCount - 2) {
                    words.add(loremWords.random())
                }
            } else {
                repeat(wordCount) {
                    words.add(loremWords.random())
                }
            }
            
            // Capitalize first word and add period
            val sentence = words.joinToString(" ")
            sentences.add(sentence.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() } + ".")
        }
        
        return sentences.joinToString(" ")
    }
}