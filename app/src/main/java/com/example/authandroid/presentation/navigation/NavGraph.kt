    // presentation/navigation/NavGraph.kt
    package com.example.authandroid.presentation.navigation

    import androidx.compose.runtime.Composable
    import androidx.navigation.NavHostController
    import androidx.navigation.compose.NavHost
    import androidx.navigation.compose.composable
    import com.tuapp.presentation.auth.login.HomeScreen
    import com.tuapp.presentation.auth.login.LoginScreen
    import com.tuapp.presentation.auth.register.RegisterScreen

    @Composable
    fun AppNavGraph(
        navController: NavHostController
    ) {
        NavHost(
            navController = navController,
            startDestination = Routes.REGISTER
        ) {
            composable(Routes.REGISTER) {
                RegisterScreen(
                    onRegisterSuccess = {
                        navController.navigate(Routes.LOGIN) {
                            popUpTo(Routes.REGISTER) { inclusive = true }
                        }
                    },
                    onNavigateToLogin = {
                        navController.navigate(Routes.LOGIN)
                    }
                )
            }


            composable(Routes.LOGIN) {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.LOGIN) { inclusive = true }
                        }
                    },
                    onNavigateToRegister = {
                        navController.navigate(Routes.REGISTER)
                    }
                )
            }
            composable(Routes.HOME) {
                HomeScreen()
            }
        }
    }
