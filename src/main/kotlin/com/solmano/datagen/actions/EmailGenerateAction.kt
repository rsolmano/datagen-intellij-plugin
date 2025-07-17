package com.solmano.datagen.actions

import kotlin.random.Random.Default.nextInt

class GenerateEmailAction : BaseGenerateAction() {
    
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