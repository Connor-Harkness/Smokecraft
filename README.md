# Smokecraft

![Minecraft](https://img.shields.io/badge/Minecraft-1.21.8-brightgreen)
![NeoForge](https://img.shields.io/badge/NeoForge-21.8.40-blue)
![Version](https://img.shields.io/badge/Version-1.0.0-orange)

**⚠️ Health Disclaimer: This mod is for entertainment purposes only. Smoking is harmful to health and can cause serious medical conditions including cancer, heart disease, and other life-threatening illnesses. This mod does not promote or encourage real-world smoking. Always smoke responsibly and be aware of health risks.**

Smokecraft is a Minecraft mod that adds cigarettes, lighters, and smoking mechanics to the game. The mod focuses on providing a realistic smoking experience with configurable effects, visual elements, and sound design while maintaining awareness of health implications.

## 🚀 Features

### Items
- **Cigarettes** - Smokable items that produce particle effects and sounds
- **Lighter** - Required tool to smoke cigarettes with limited durability
- **Cigarette Butts** - Leftover items after smoking (configurable)

### Visual Effects
- Realistic smoke particle effects while smoking
- Optional glowing ember tip on cigarettes
- Configurable particle density
- Immersive sound effects (ignition, smoking, extinguishing)

### Configuration System
- **Cosmetic Mode** - Pure visual/audio effects with no gameplay impact (default)
- **Effect Mode** - Optional gameplay effects including hunger, speed, and status effects
- **Health Warnings** - Configurable health reminder messages
- **Customizable Settings** - Extensive configuration options for all aspects

## 📦 Installation

1. **Download Prerequisites:**
   - Minecraft 1.21.8
   - NeoForge 21.8.40 or later

2. **Install the Mod:**
   - Download the latest Smokecraft jar file
   - Place it in your `mods` folder
   - Launch Minecraft with the NeoForge profile

3. **Configuration:**
   - Configuration file will be generated at `config/smokecraft-common.toml`
   - Modify settings as desired and restart the game

## 🎮 How to Use

### Basic Usage
1. Obtain a **Lighter** and **Cigarettes** (currently available in Creative mode or via commands)
2. Hold a cigarette in one hand and have a lighter available (in the other hand or inventory)
3. Right-click to start smoking
4. A smoking animation will play with particle effects and sounds
5. After finishing, you'll receive a **Cigarette Butt** (if enabled in config)

### Controls
- **Right-click** with cigarette to start smoking (requires lighter)
- **Right-click** with lighter to ignite (consumes durability)
- **Release right-click** to stop smoking early

## 📋 Items Reference

### Cigarette
- **Stack Size:** 64
- **Usage:** Right-click to smoke (requires lighter)
- **Duration:** ~4 seconds (configurable)
- **Effects:** Produces smoke particles and sound effects
- **Result:** Leaves behind a cigarette butt when finished

### Lighter
- **Stack Size:** 1
- **Durability:** 100 uses (configurable)
- **Usage:** Required to smoke cigarettes
- **Effects:** Makes ignition sound and consumes durability per use
- **Repair:** Not repairable (consumable item)

### Cigarette Butt
- **Stack Size:** 64
- **Source:** Leftover from smoking cigarettes
- **Purpose:** Waste item (can be disabled in config)
- **Future:** May have composting recipes in future versions

## ⚙️ Configuration Options

The mod includes extensive configuration options in `config/smokecraft-common.toml`:

### General Settings
- `show_health_warning` (default: true) - Display health warnings on player join
- `cosmetic_only` (default: true) - Enable only visual/audio effects without gameplay impact

### Cigarette Settings
- `use_duration` (default: 80 ticks) - Time to smoke a cigarette
- `hunger_drain` (default: 1) - Hunger cost when not in cosmetic mode
- `butt_enabled` (default: false) - Whether cigarette butts are dropped
- `butt_compost_chance` (default: 0.1) - Chance for butts to be compostable

### Lighter Settings
- `durability` (default: 100) - Number of uses before lighter breaks

### Effect Settings (Only Active When cosmetic_only = false)
- `enable_nicotine_rush` (default: false) - Speed boost effect
- `nicotine_rush_duration` (default: 200 ticks) - Duration of speed boost
- `enable_hunger_effect` (default: false) - Hunger drain while smoking
- `enable_nausea_effect` (default: false) - Nausea effect for new smokers

### Visual Settings
- `smoke_particle_density` (default: 0.5) - Amount of smoke particles
- `enable_ember_tip` (default: true) - Glowing tip on cigarettes

## 🔧 Current Limitations

- **No Crafting Recipes:** Items are currently only available through Creative mode or commands
- **Recipe System:** Crafting recipes are planned for future releases
- **Limited Items:** Currently only includes basic cigarettes and lighter

## 🛠️ Development

This mod is built using:
- **Java 21** - Target language version
- **NeoForge 21.8.40** - Mod framework
- **Gradle 8.8** - Build system

### Building from Source
```bash
git clone https://github.com/Connor-Harkness/Smokecraft.git
cd Smokecraft
./gradlew build
```

## 📞 Support & Issues

If you encounter bugs or have feature suggestions:
1. Check existing issues on GitHub
2. Create a new issue with detailed information
3. Include your mod version, Minecraft version, and crash logs if applicable

## 📜 License

All Rights Reserved - See license information in the mod files.

## 🤝 Contributing

Contributions are welcome! Please:
1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request with clear description

## ⚠️ Final Health Reminder

This modification is purely for entertainment within the Minecraft game environment. It is not intended to promote or encourage real-world smoking. Smoking tobacco products is harmful to health and can cause:

- Cancer (lung, throat, mouth, and others)
- Heart disease and stroke  
- Respiratory diseases
- Reduced life expectancy
- Addiction and withdrawal symptoms

If you smoke in real life, consider seeking help to quit. Many resources are available to support smoking cessation.