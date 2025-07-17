# datagen-intellij-plugin

![Build](https://github.com/rinat-solmano/datagen-intellij-plugin/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/25260-datagen.svg)](https://plugins.jetbrains.com/plugin/25260-datagen)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/25260-datagen.svg)](https://plugins.jetbrains.com/plugin/25260-datagen)

<!-- Plugin description -->
# Datagen IntelliJ-based Plugin
The lightweight plugin for generating data for testing, samples, demos, etc.

## Features

### Data Generation
- **UUID Generation** - Generate and insert random UUIDs
- **Number Generation** - Generate random integers (1-999 or 1-999999)
- **Email Generation** - Generate realistic email addresses with various patterns
- **Name Generation** - Generate realistic names for testing and demos:
  - **First Names** - Culturally diverse first names (Western and international)
  - **Last Names** - Diverse surnames from multiple cultures
  - **Full Names** - Complete names combining first and last names
- **Lorem Ipsum Text Generation** - Generate placeholder text for mockups and testing:
  - **Single Word** - Generate random Lorem Ipsum words
  - **Sentence** - Generate sentences with 8-15 words
  - **Short Paragraph** - Generate paragraphs with 3-5 sentences
  - **Long Paragraph** - Generate paragraphs with 8-11 sentences

### Quick Access
All generators are available through the **Generate** menu (Code → Generate) or by right-clicking in the editor.

#### UUID generation
![UUID Generation](https://media.giphy.com/media/5mjNmKx0mugOH01QiF/giphy.gif)

#### Numbers generation
![Numbers Generation](https://media.giphy.com/media/OvTqNwi4hABib4hHq3/giphy.gif)

#### Name generation
*Generate realistic names for test users, database population, and demos*

<!-- Plugin description end -->

## Installation

- Using the IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "datagen-intellij-plugin"</kbd> >
  <kbd>Install</kbd>
  
- Using JetBrains Marketplace:

  Go to [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/25260-datagen) and install it by clicking the <kbd>Install to ...</kbd> button in case your IDE is running.

  You can also download the [latest release](https://plugins.jetbrains.com/plugin/25260-datagen/versions) from JetBrains Marketplace and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

- Manually:

  Download the [latest release](https://github.com/rinat-solmano/datagen-intellij-plugin/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
