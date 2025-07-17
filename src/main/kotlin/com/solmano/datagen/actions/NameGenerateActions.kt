package com.solmano.datagen.actions

class GenerateFirstNameAction : BaseGenerateAction() {
    
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

class GenerateLastNameAction : BaseGenerateAction() {
    
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

class GenerateFullNameAction : BaseGenerateAction() {
    
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