# MealM8 - Your Personal Meal Companion

A modern Android app built with Jetpack Compose that helps you discover, plan, and enjoy meals. MealM8 connects to TheMealDB API to provide a comprehensive meal planning experience.

## 🎯 Project Overview

MealM8 is designed to be your go-to app for meal discovery and planning. Whether you're looking for new recipes, searching for specific ingredients, or organizing your favorite meals, MealM8 provides an intuitive and beautiful interface to enhance your culinary journey.

## ✨ Current Features

### 🎨 Design System
- **Typography**: Plus Jakarta Sans font family with 4 weights (Regular, Medium, Bold, ExtraBold)
- **Color Palette**: Professional green theme with Material 3 design
- **Theme Support**: Light and dark mode compatibility
- **Material 3**: Modern Android design components and animations

### 📱 User Interface
- **Splash Screen**: Animated intro with professional branding
- **Bottom Navigation**: 4-tab navigation (Home, Categories, Search, Favourites)
- **Responsive Design**: Optimized for different screen sizes
- **Smooth Animations**: Material 3 transitions and micro-interactions

### 🏗️ Architecture
- **Clean Architecture**: Well-organized code structure
- **MVVM Pattern**: Separation of concerns with ViewModels
- **Dependency Injection**: Koin for modular dependency management
- **Navigation**: Compose Navigation with type-safe routing

### 🔌 API Integration
- **TheMealDB V2 API**: Integration with comprehensive meal database
- **Base API Service**: Extensible architecture for future API additions
- **Error Handling**: Robust error management and retry mechanisms
- **Data Models**: Type-safe data structures for meal information

### 🛠️ Technical Stack
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM + Clean Architecture
- **Dependency Injection**: Koin
- **Networking**: Ktor HTTP Client
- **Serialization**: Kotlinx Serialization
- **Local Storage**: DataStore Preferences
- **Navigation**: Compose Navigation


## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 17 or later
- Android SDK 24 or later

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/assad62/mealm8.git
   cd mealm8
   ```

2. Open the project in Android Studio

3. Sync the project with Gradle files

4. Run the app on an emulator or physical device

### API Configuration
The app uses TheMealDB V2 API. The API key is configured in `local.properties`:
```properties
THEMEALDB_API_KEY=your_api_key_here
```

## 📋 What's Implemented

### ✅ Completed Features
- [x] **Project Setup**: Android project with Jetpack Compose
- [x] **Design System**: Complete typography and color system
- [x] **Splash Screen**: Animated intro with branding
- [x] **Bottom Navigation**: 4-tab navigation structure
- [x] **API Integration**: TheMealDB V2 API service
- [x] **Clean Architecture**: MVVM pattern with dependency injection
- [x] **Theme Support**: Light/dark mode compatibility
- [x] **Navigation**: Type-safe navigation between screens
- [x] **Code Cleanup**: Removed unused files and dead code

### 🎨 UI/UX Features
- [x] **Material 3 Design**: Modern Android design language
- [x] **Custom Typography**: Plus Jakarta Sans font integration
- [x] **Color System**: Professional green theme palette
- [x] **Responsive Layout**: Adaptive to different screen sizes
- [x] **Smooth Animations**: Material 3 transitions
- [x] **Accessibility**: Proper content descriptions and semantic structure

### 🔧 Technical Features
- [x] **Dependency Injection**: Koin configuration
- [x] **API Client**: Ktor HTTP client with error handling
- [x] **Data Models**: Type-safe meal data structures
- [x] **Repository Pattern**: Clean data layer abstraction
- [x] **State Management**: Compose state handling
- [x] **Build Configuration**: Gradle setup with proper dependencies

## 🚧 What's Left to Implement

### 📱 Core Features
- [ ] **Home Screen**: Dashboard with featured meals and quick actions
- [ ] **Categories Screen**: Browse meals by category (Beef, Chicken, Dessert, etc.)
- [ ] **Search Screen**: Search functionality with filters and suggestions
- [ ] **Favourites Screen**: Save and manage favorite meals
- [ ] **Meal Details**: Detailed view with ingredients, instructions, and images
- [ ] **Meal Planning**: Weekly meal planning and calendar integration

### 🔍 Search & Discovery
- [ ] **Search by Name**: Find meals by recipe name
- [ ] **Search by Ingredient**: Find meals containing specific ingredients
- [ ] **Search by Category**: Filter meals by food category
- [ ] **Search by Area**: Find meals by cuisine/region
- [ ] **Advanced Filters**: Multiple filter combinations
- [ ] **Search History**: Recent searches and suggestions

### 💾 Data Management
- [ ] **Favorites System**: Add/remove meals from favorites
- [ ] **Meal History**: Track previously viewed meals
- [ ] **User Preferences**: Customizable app settings
- [ ] **Offline Support**: Cache meals for offline viewing
- [ ] **Data Sync**: Sync favorites across devices

### 🎨 Enhanced UI/UX
- [ ] **Meal Cards**: Beautiful meal display cards
- [ ] **Image Loading**: Efficient image loading and caching
- [ ] **Pull to Refresh**: Refresh content with gestures
- [ ] **Infinite Scroll**: Pagination for meal lists
- [ ] **Loading States**: Skeleton screens and progress indicators
- [ ] **Error States**: User-friendly error messages

### 🔧 Technical Improvements
- [ ] **Unit Tests**: Comprehensive test coverage
- [ ] **UI Tests**: Automated UI testing
- [ ] **Performance Optimization**: Image optimization and lazy loading
- [ ] **Analytics**: User behavior tracking
- [ ] **Crash Reporting**: Error monitoring and reporting
- [ ] **CI/CD**: Automated build and deployment
- [ ] **Project Structure Documentation**: Add detailed project structure diagram to README

## 🛠️ Development Roadmap

### Phase 1: Core Functionality (Current)
- [x] Project setup and architecture
- [x] Design system implementation
- [x] Basic navigation structure
- [x] API integration foundation

### Phase 2: Meal Discovery (Next)
- [ ] Implement Home screen with featured meals
- [ ] Add Categories screen with meal browsing
- [ ] Create Search functionality
- [ ] Build meal detail screens

### Phase 3: User Features
- [ ] Favorites system
- [ ] User preferences
- [ ] Meal history
- [ ] Enhanced search filters

### Phase 4: Advanced Features
- [ ] Meal planning calendar
- [ ] Shopping list generation
- [ ] Social features (sharing, reviews)
- [ ] Offline support


## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- [TheMealDB](https://www.themealdb.com/) for providing the meal database API
- [Google Fonts](https://fonts.google.com/) for Plus Jakarta Sans typography
- [Material Design](https://material.io/) for design guidelines
- [Jetpack Compose](https://developer.android.com/jetpack/compose) for modern Android UI

## 📞 Contact

- **Developer**: Mohammad Assad
- **GitHub**: [@assad62](https://github.com/assad62)
- **Project Link**: [https://github.com/assad62/mealm8](https://github.com/assad62/mealm8)

---

**MealM8** - Discover • Plan • Enjoy 🍽️
