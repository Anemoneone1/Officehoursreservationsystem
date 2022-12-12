package mainPackage.view

import androidx.compose.foundation.Image



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.officehoursreservationsystem.R
import mainPackage.view.R



@OptIn(ExperimentalComposeUiApi::class)
@Composable
//@Prev
fun LoginPage(navController: NavController) {


    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val passwordVisibility = remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }



    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White), contentAlignment = Alignment.TopCenter
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        backgroundColor = colorResource(R.color.back),
                        title = { Text(text = " ") },
                        navigationIcon = {
                            Icon(
                                painter = painterResource(id = com.example.frontendtry1.R.drawable.pwr_logo_en),
                                contentDescription = "Image",

                                )
                        },
                        actions = {}
                    )
                }) {
            }

        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth(0.60f)
                .clip(RoundedCornerShape(10.dp))
                .background(whiteBackground)
                .padding(10.dp)
        )


        {
            Text(
                text = "Sign in",
                style = TextStyle(
                    fontWeight = FontWeight.W400,
                    letterSpacing = 1.sp
                ),
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.padding(20.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = emailValue.value,
                    onValueChange = { emailValue.value = it },
                    label = { Text(text = "Email address") },
                    placeholder = { Text(text = "Email address") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                OutlinedTextField(
                    value = passwordValue.value,
                    onValueChange = { passwordValue.value = it },
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordVisibility.value = !passwordVisibility.value
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.password_eye),
                                contentDescription = stringResource(id = R.string.app_name)
                            )
                        }
                    },
                    label = { Text(text = "Password") },
                    placeholder = { Text(text = "Password") },
                    singleLine = true,
                    visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .focusRequester(focusRequester = focusRequester),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            //...
                            keyboardController?.hide()
                        }
                    )
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)
                        .clip(RoundedCornerShape(65.dp)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.Red_button))
                ) {
                    Text(
                        text = "Sign In",
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.white)
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))
                Text(
                    text = "Create An Account",
                )
                Spacer(modifier = Modifier.padding(20.dp))
            }
        }

    }
}