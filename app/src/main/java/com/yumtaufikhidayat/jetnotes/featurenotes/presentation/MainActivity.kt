package com.yumtaufikhidayat.jetnotes.featurenotes.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yumtaufikhidayat.jetnotes.featurenotes.presentation.addeditnotes.AddEditNotesScreen
import com.yumtaufikhidayat.jetnotes.featurenotes.presentation.notes.NotesScreen
import com.yumtaufikhidayat.jetnotes.featurenotes.presentation.utils.Screen
import com.yumtaufikhidayat.jetnotes.featurenotes.presentation.utils.Utils
import com.yumtaufikhidayat.jetnotes.ui.theme.JetNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetNotesTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val notesColor = Utils.KEY_NOTES_COLOR
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NotesScreen.route
                    ) {
                        composable(route = Screen.NotesScreen.route) {
                            NotesScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditNotesScreen.route +
                                    "?notesId={notesId}&notesColor={notesColor}",
                            arguments = listOf(
                                navArgument(
                                    name = Utils.KEY_NOTES_ID
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = notesColor
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            val color = it.arguments?.getInt(notesColor) ?: -1
                            AddEditNotesScreen(navController = navController, notesColor = color)
                        }
                    }
                }
            }
        }
    }
}