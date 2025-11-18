// This is your full code for MainActivity.kt
// Replace everything in the file with this:

package com.appathon.bunkr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.appathon.bunkr.ui.theme.BunkrTheme // This import might be slightly different
import kotlin.math.ceil
import kotlin.math.floor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // BunkrTheme is a default theme set up by Android Studio
            BunkrTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AttendanceScreen()
                }
            }
        }
    }
}

/**
 * The main screen that holds all our calculators.
 * LazyColumn is just a scrollable column.
 */
@Composable
fun AttendanceScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp) // Space between cards
    ) {
        // App Title
        item {
            Text(
                text = "bunkr",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "SDG 4: Quality Education",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }

        // Add each calculator as an item in the list
        item {
            SimplePercentageCalculator()
        }
        item {
            MaxMissableCalculator()
        }
        item {
            ClassesToAttendCalculator()
        }
    }
}

/**
 * CALCULATOR 1: Simple Attendance Percentage
 * Based on formula: (Classes Attended / Total Classes) * 100 [cite: 6]
 */
@Composable
fun SimplePercentageCalculator() {
    // These 'remember' variables hold the text in the boxes
    var attended by remember { mutableStateOf("") }
    var total by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(4.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "1. Simple Percentage",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Text box for "Classes Attended"
            OutlinedTextField(
                value = attended,
                onValueChange = { attended = it },
                label = { Text("Classes Attended") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Text box for "Total Classes"
            OutlinedTextField(
                value = total,
                onValueChange = { total = it },
                label = { Text("Total Classes") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val numAttended = attended.toDoubleOrNull() ?: 0.0
                    val numTotal = total.toDoubleOrNull() ?: 0.0

                    result = if (numTotal > 0) {
                        // This is the calculation from your PDF [cite: 6]
                        val percentage = (numAttended / numTotal) * 100
                        "Your attendance is ${String.format("%.2f", percentage)}%"
                    } else {
                        "Total classes must be more than 0."
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calculate Percentage")
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Display the result
            Text(text = result, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

/**
 * CALCULATOR 2: Max Classes You Can Miss
 * Based on formula: M = T * (1 - p/100) [cite: 34]
 */
@Composable
fun MaxMissableCalculator() {
    var finalTotal by remember { mutableStateOf("") }
    var desiredPercent by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(4.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "2. Max Bunks Calculator",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = finalTotal,
                onValueChange = { finalTotal = it },
                label = { Text("Final Total Classes in Semester") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = desiredPercent,
                onValueChange = { desiredPercent = it },
                label = { Text("Desired Attendance % (e.g., 75)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val numFinalTotal = finalTotal.toDoubleOrNull() ?: 0.0
                    val numDesired = desiredPercent.toDoubleOrNull() ?: 0.0

                    result = if (numFinalTotal > 0 && numDesired > 0) {
                        // This is the simplified formula from your PDF [cite: 34]
                        val p = numDesired / 100.0
                        val maxMiss = numFinalTotal * (1.0 - p)
                        "To get $numDesired%, you can miss a TOTAL of ${floor(maxMiss).toInt()} classes."
                    } else {
                        "Please enter valid numbers."
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calculate Max Bunks")
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = result, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

/**
 * CALCULATOR 3: Classes Needed to Reach Target
 * Based on the goal of section 2 in your PDF
 */
@Composable
fun ClassesToAttendCalculator() {
    var currentAttended by remember { mutableStateOf("") }
    var currentTotal by remember { mutableStateOf("") }
    var desiredPercent by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(4.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "3. Classes to Reach Target",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = currentAttended,
                onValueChange = { currentAttended = it },
                label = { Text("Classes ALREADY Attended") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = currentTotal,
                onValueChange = { currentTotal = it },
                label = { Text("Current Total Classes") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = desiredPercent,
                onValueChange = { desiredPercent = it },
                label = { Text("Desired Attendance % (e.g., 75)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val A = currentAttended.toDoubleOrNull() ?: 0.0
                    val T = currentTotal.toDoubleOrNull() ?: 0.0
                    val p = desiredPercent.toDoubleOrNull() ?: 0.0

                    // This logic solves for 'x' in the equation:
                    // (A + x) / (T + x) = p / 100
                    // This matches the goal of "how many more classes to attend"

                    val currentPercentage = if (T > 0) (A / T) * 100 else 0.0

                    result = if (p <= currentPercentage) {
                        "You already have ${String.format("%.2f", currentPercentage)}%!"
                    } else if (p >= 100) {
                        "You can't reach 100% (or more) unless you've attended all classes."
                    } else {
                        val numerator = (p * T) - (100 * A)
                        val denominator = 100 - p
                        val classesNeeded = ceil(numerator / denominator)

                        if (classesNeeded > 0) {
                            "You must attend ${classesNeeded.toInt()} more classes in a row."
                        } else {
                            "You already meet this requirement."
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calculate Classes Needed")
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = result, style = MaterialTheme.typography.bodyLarge)
        }
    }
}