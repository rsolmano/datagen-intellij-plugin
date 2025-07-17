package com.solmano.datagen.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.CaretModel
import java.util.*
import kotlin.random.Random.Default.nextInt

abstract class SimpleGenerateAndInsertActions : AnAction() {

    protected abstract fun generate(): String
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project ?: return
        val editor = event.getData(com.intellij.openapi.actionSystem.CommonDataKeys.EDITOR) ?: return

        WriteCommandAction.runWriteCommandAction(project) {
            val caretModel: CaretModel = editor.caretModel
            editor.document.insertString(caretModel.offset, generate())
        }
    }
}

class GenerateUUIDAction : SimpleGenerateAndInsertActions() {
    override fun generate(): String = UUID.randomUUID().toString()
}

class Generate1to999Action : SimpleGenerateAndInsertActions() {
    override fun generate(): String = (nextInt(1, 1000)).toString()
}

class Generate1to999999Action : SimpleGenerateAndInsertActions() {
    override fun generate(): String = (nextInt(1, 1000000)).toString()
}

class GenerateEmailAction : SimpleGenerateAndInsertActions() {
    
    private val firstNames = listOf(
        "john", "jane", "michael", "sarah", "david", "emily", "robert", "jessica",
        "william", "ashley", "james", "amanda", "christopher", "melissa", "daniel",
        "stephanie", "matthew", "nicole", "anthony", "elizabeth", "mark", "helen",
        "donald", "deborah", "steven", "rachel", "paul", "carolyn", "andrew", "janet"
    )
    
    private val lastNames = listOf(
        "smith", "johnson", "williams", "brown", "jones", "garcia", "miller", "davis",
        "rodriguez", "martinez", "hernandez", "lopez", "gonzalez", "wilson", "anderson",
        "thomas", "taylor", "moore", "jackson", "martin", "lee", "perez", "thompson",
        "white", "harris", "sanchez", "clark", "ramirez", "lewis", "robinson"
    )
    
    private val domains = listOf(
        "gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "company.com",
        "example.com", "test.com", "demo.com", "sample.org", "acme.com",
        "techcorp.com", "business.net", "enterprise.org", "startup.io", "dev.com"
    )
    
    private val emailPatterns = listOf(
        { first: String, last: String, domain: String -> "$first.$last@$domain" },
        { first: String, last: String, domain: String -> "$first$last@$domain" },
        { first: String, last: String, domain: String -> "${first.first()}$last@$domain" },
        { first: String, _: String, domain: String -> "$first${nextInt(1, 999)}@$domain" },
        { first: String, last: String, domain: String -> "$first.${last.first()}@$domain" }
    )
    
    override fun generate(): String {
        val firstName = firstNames.random()
        val lastName = lastNames.random()
        val domain = domains.random()
        val pattern = emailPatterns.random()
        
        return pattern(firstName, lastName, domain)
    }
}

class GenerateFirstNameAction : SimpleGenerateAndInsertActions() {
    
    private val firstNames = listOf(
        // Western names
        "Alexander", "Alexandra", "Andrew", "Anna", "Anthony", "Ashley", "Benjamin", "Brittany",
        "Christopher", "Christina", "Daniel", "Danielle", "David", "Emily", "Elizabeth", "Emma",
        "James", "Jane", "Jessica", "John", "Jonathan", "Joseph", "Joshua", "Julia",
        "Kevin", "Laura", "Mark", "Mary", "Matthew", "Melissa", "Michael", "Michelle",
        "Nicholas", "Nicole", "Paul", "Rachel", "Robert", "Sarah", "Steven", "Stephanie",
        "Thomas", "Victoria", "William", "Amanda", "Brian", "Jennifer", "Charles", "Lisa",
        
        // International names
        "Akira", "Aisha", "Ali", "Amara", "Arjun", "Camila", "Chen", "Diego",
        "Elena", "Fatima", "Hassan", "Hiroshi", "Isabella", "Jamal", "Kenji", "Layla",
        "Luis", "Maria", "Nadia", "Omar", "Priya", "Rafael", "Sakura", "Sofia",
        "Tariq", "Valentina", "Wei", "Yuki", "Zara", "Dmitri", "Ingrid", "Klaus",
        "Olga", "Pierre", "Svetlana", "Yuki", "Anastasia", "Boris", "Katarina", "Mikhail"
    )
    
    override fun generate(): String = firstNames.random()
}

class GenerateLastNameAction : SimpleGenerateAndInsertActions() {
    
    private val lastNames = listOf(
        // Western surnames
        "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis",
        "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson",
        "Thomas", "Taylor", "Moore", "Jackson", "Martin", "Lee", "Perez", "Thompson",
        "White", "Harris", "Sanchez", "Clark", "Ramirez", "Lewis", "Robinson", "Walker",
        "Young", "Allen", "King", "Wright", "Scott", "Torres", "Nguyen", "Hill",
        "Flores", "Green", "Adams", "Nelson", "Baker", "Hall", "Rivera", "Campbell",
        
        // International surnames
        "Chen", "Wang", "Li", "Zhang", "Liu", "Yang", "Huang", "Zhao",
        "Wu", "Zhou", "Xu", "Sun", "Ma", "Zhu", "Hu", "Guo",
        "Singh", "Kumar", "Sharma", "Gupta", "Khan", "Ahmed", "Ali", "Hassan",
        "Patel", "Shah", "Mehta", "Jain", "Agarwal", "Bansal", "Chopra", "Malhotra",
        "Müller", "Schmidt", "Schneider", "Fischer", "Weber", "Meyer", "Wagner", "Becker",
        "Schulz", "Hoffmann", "Schäfer", "Koch", "Bauer", "Richter", "Klein", "Wolf",
        "Sato", "Suzuki", "Takahashi", "Tanaka", "Watanabe", "Ito", "Yamamoto", "Nakamura"
    )
    
    override fun generate(): String = lastNames.random()
}

class GenerateFullNameAction : SimpleGenerateAndInsertActions() {
    
    private val firstNames = listOf(
        // Western names
        "Alexander", "Alexandra", "Andrew", "Anna", "Anthony", "Ashley", "Benjamin", "Brittany",
        "Christopher", "Christina", "Daniel", "Danielle", "David", "Emily", "Elizabeth", "Emma",
        "James", "Jane", "Jessica", "John", "Jonathan", "Joseph", "Joshua", "Julia",
        "Kevin", "Laura", "Mark", "Mary", "Matthew", "Melissa", "Michael", "Michelle",
        "Nicholas", "Nicole", "Paul", "Rachel", "Robert", "Sarah", "Steven", "Stephanie",
        "Thomas", "Victoria", "William", "Amanda", "Brian", "Jennifer", "Charles", "Lisa",
        
        // International names
        "Akira", "Aisha", "Ali", "Amara", "Arjun", "Camila", "Chen", "Diego",
        "Elena", "Fatima", "Hassan", "Hiroshi", "Isabella", "Jamal", "Kenji", "Layla",
        "Luis", "Maria", "Nadia", "Omar", "Priya", "Rafael", "Sakura", "Sofia",
        "Tariq", "Valentina", "Wei", "Yuki", "Zara", "Dmitri", "Ingrid", "Klaus",
        "Olga", "Pierre", "Svetlana", "Yuki", "Anastasia", "Boris", "Katarina", "Mikhail"
    )
    
    private val lastNames = listOf(
        // Western surnames
        "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis",
        "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson",
        "Thomas", "Taylor", "Moore", "Jackson", "Martin", "Lee", "Perez", "Thompson",
        "White", "Harris", "Sanchez", "Clark", "Ramirez", "Lewis", "Robinson", "Walker",
        "Young", "Allen", "King", "Wright", "Scott", "Torres", "Nguyen", "Hill",
        "Flores", "Green", "Adams", "Nelson", "Baker", "Hall", "Rivera", "Campbell",
        
        // International surnames
        "Chen", "Wang", "Li", "Zhang", "Liu", "Yang", "Huang", "Zhao",
        "Wu", "Zhou", "Xu", "Sun", "Ma", "Zhu", "Hu", "Guo",
        "Singh", "Kumar", "Sharma", "Gupta", "Khan", "Ahmed", "Ali", "Hassan",
        "Patel", "Shah", "Mehta", "Jain", "Agarwal", "Bansal", "Chopra", "Malhotra",
        "Müller", "Schmidt", "Schneider", "Fischer", "Weber", "Meyer", "Wagner", "Becker",
        "Schulz", "Hoffmann", "Schäfer", "Koch", "Bauer", "Richter", "Klein", "Wolf",
        "Sato", "Suzuki", "Takahashi", "Tanaka", "Watanabe", "Ito", "Yamamoto", "Nakamura"
    )
    
    override fun generate(): String {
        val firstName = firstNames.random()
        val lastName = lastNames.random()
        return "$firstName $lastName"
    }
}

class GenerateLoremWordAction : SimpleGenerateAndInsertActions() {
    
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

class GenerateLoremSentenceAction : SimpleGenerateAndInsertActions() {
    
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

class GenerateLoremShortParagraphAction : SimpleGenerateAndInsertActions() {
    
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
    
    private fun generateSentence(): String {
        val wordCount = nextInt(8, 16) // 8-15 words per sentence
        val words = mutableListOf<String>()
        
        repeat(wordCount) {
            words.add(loremWords.random())
        }
        
        // Capitalize first word and add period
        val sentence = words.joinToString(" ")
        return sentence.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() } + "."
    }
    
    override fun generate(): String {
        val sentenceCount = nextInt(3, 6) // 3-5 sentences per paragraph
        val sentences = mutableListOf<String>()
        
        // First sentence starts with "Lorem ipsum"
        val firstSentence = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        sentences.add(firstSentence)
        
        // Add remaining sentences
        repeat(sentenceCount - 1) {
            sentences.add(generateSentence())
        }
        
        return sentences.joinToString(" ")
    }
}

class GenerateLoremLongParagraphAction : SimpleGenerateAndInsertActions() {
    
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
    
    private fun generateSentence(): String {
        val wordCount = nextInt(8, 16) // 8-15 words per sentence
        val words = mutableListOf<String>()
        
        repeat(wordCount) {
            words.add(loremWords.random())
        }
        
        // Capitalize first word and add period
        val sentence = words.joinToString(" ")
        return sentence.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() } + "."
    }
    
    override fun generate(): String {
        val sentenceCount = nextInt(8, 12) // 8-11 sentences per long paragraph
        val sentences = mutableListOf<String>()
        
        // First sentence starts with "Lorem ipsum"
        val firstSentence = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        sentences.add(firstSentence)
        
        // Add remaining sentences
        repeat(sentenceCount - 1) {
            sentences.add(generateSentence())
        }
        
        return sentences.joinToString(" ")
    }
}
