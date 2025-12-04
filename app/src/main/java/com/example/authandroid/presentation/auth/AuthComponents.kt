package com.example.authandroid.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun AuthBackground(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color(0xFF5B5CEB), // morado arriba
                        Color(0xFFBA68F8)  // rosa abajo
                    )
                )
            ),
        contentAlignment = Alignment.Center,
        content = content
    )
}

@Composable
fun AuthScaffold(
    title: String,
    subtitle: String,
    content: @Composable ColumnScope.() -> Unit,
    bottomText: @Composable () -> Unit
) {
    AuthBackground {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            Box(
                modifier = Modifier
                    .size(88.dp)
                    .background(Color(0x33FFFFFF), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.SportsEsports,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }

            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFFE0E0E0)
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = content
            )

            Spacer(modifier = Modifier.height(8.dp))

            bottomText()

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: ImageVector,
    isPassword: Boolean = false
) {
    val visualTransformation =
        if (isPassword) PasswordVisualTransformation() else VisualTransformation.None

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = null) },
        singleLine = true,
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(size = 24.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color(0x22FFFFFF),
            unfocusedContainerColor = Color(0x22FFFFFF),
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color(0x55FFFFFF),
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color(0xFFD0D0FF),
            cursorColor = Color.White,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White
        )
    )
}

@Composable
fun AuthBottomLink(
    normalText: String,
    actionText: String,
    onClick: () -> Unit
) {
    val annotated = buildAnnotatedString {
        append(normalText)
        pushStringAnnotation(tag = "ACTION", annotation = "ACTION")
        withStyle(
            style = SpanStyle(
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        ) {
            append(actionText)
        }
        pop()
    }

    ClickableText(
        text = annotated,
        style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFFE0E0E0)),
        onClick = { offset ->
            annotated.getStringAnnotations("ACTION", offset, offset)
                .firstOrNull()?.let { onClick() }
        }
    )
}