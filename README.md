# Bunkr  
### attend smartly  

Bunkr is a simple, lightweight attendance calculator designed to help students track, improve, and plan their attendance intelligently.  
This tool is available as an Android app (Kotlin)

---

## Features

### **1. Simple Attendance Percentage**
Formula:  
Attendance % = (Classes Attended / Total Classes) × 100


---

### **2. Classes Needed to Reach Target % (Your Formula)**
Goal:  
“How many classes until my attendance reaches 95% (or any target)?”

Definitions:
Missed = TotalClasses - Attended
(X - Missed) / X = G / 100


Solve for required total classes:
X = Missed / (1 - G/100)


Extra classes needed:
Extra = X - CurrentTotalClasses


---

### **3. How Many Classes You Can Still Miss**
Formula:
Max Missable = TotalClasses - (TotalClasses/100 × DesiredPercentage)

Example:
60 - (60/100 × 95)

---

## HTML Version (Static Website)

A complete static `index.html` is provided with:
- clean layout  
- simple styling  
- JavaScript implementation of all formulas  
- mobile-friendly input fields  
- ready for GitHub Pages hosting  

---

## Android Version (Kotlin)

The app includes:
- `MainActivity.kt` containing full formula logic  
- `activity_main.xml` UI layout  
- AndroidX + Kotlin Gradle configuration  
- Full implementation of all three calculators  

It runs on any device with Android 5.0+ (SDK 21+).

---

## Repository Contents

index.html → static attendance calculator website
README.md → documentation
MainActivity.kt → Android logic
activity_main.xml → Android UI
AndroidManifest.xml → app configuration
build.gradle → dependencies & Kotlin setup

---

## Why Bunkr?

Students need a fast, accurate, no-nonsense tool to calculate:
- current attendance  
- how many classes they need to attend  
- how many classes they can still miss  

Bunkr removes guesswork and gives instant clarity.

---

## Improvements

Improvements available on request:
- UI redesign (aesthetic / Material You)  
- dark mode version  
- class tracking & saving  
- downloadable APK  
- custom domain setup for GitHub Pages  
- icons, logo, and favicon  

---

Made with ❤️ to help students attend smartly.
