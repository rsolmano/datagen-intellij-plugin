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
