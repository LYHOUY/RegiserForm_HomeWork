package com.example.registerform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

//import com.example.viewmodel.ui.theme.ViewModelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Registerform()
        }
    }
}

data class UserData(
    var name: String = "",
    var gender: String = "",
    var phone: String = "",
    var address: String = ""
)

class ResgisterVm : ViewModel() {
    var userData = mutableStateOf(UserData())
    fun updateUserName(input: String) {
        userData.value = userData.value.copy(
            name = input
        )
    }

    fun updateUserGender(input: String) {
        userData.value = userData.value.copy(
            gender = input
        )
    }

    fun updateUserPhone(input: String) {
        userData.value = userData.value.copy(
            phone = input
        )
    }

    fun updateUserAddress(input: String) {
        userData.value = userData.value.copy(
            address = input
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Registerform() {
    var viewModel: ResgisterVm = viewModel()
    var enabled by remember { mutableStateOf(false) }
    Column(modifier = Modifier.padding(10.dp)) {
        Row {
            Text(
                text = "Registration Form",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(bottom = 16.dp)

            )
            Spacer(modifier = Modifier.weight(90f))
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(bottom = 16.dp),
            )
        }

        TextFieldComponent(
            "Enter your name",
            "Full Name",
            { viewModel.updateUserName(it) },
            "name",
            viewModel,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextFieldComponent(
                "Gender",
                "Gender",
                { viewModel.updateUserGender(it) },
                "gender",
                viewModel,
                modifier = Modifier
                    .weight(0.3f)
                    .padding(end = 10.dp)
            )
            TextFieldComponent(
                "Phone",
                "Phone",
                { viewModel.updateUserPhone(it) },
                "phone",
                viewModel,
                modifier = Modifier.weight(0.50f),

                )
        }
        TextFieldComponent(
            "Address",
            "Address",
            { viewModel.updateUserAddress(it) },
            "address",
            viewModel,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(0.dp, 18.dp),
            enabled = if (viewModel.userData.value.name.isNotEmpty() &&
                viewModel.userData.value.gender.isNotEmpty() &&
                viewModel.userData.value.phone.isNotEmpty() &&
                viewModel.userData.value.address.isNotEmpty()
            ) true else false
        ) {
            Text(text = "Register")
        }
    }

}


@Composable
fun TextFieldComponent(
    placeholder: String,
    label: String,
    onTextChange: (text: String) -> Unit,
    event: String,
    vm: ResgisterVm,
    modifier: Modifier
) {
    OutlinedTextField(

        value = when {
            event == "name" -> vm.userData.value.name
            event == "gender" -> vm.userData.value.gender
            event == "phone" -> vm.userData.value.phone
            event == "address" -> vm.userData.value.address
            else -> ""
        },

        onValueChange = {
            onTextChange(it)
        },
        modifier,
        placeholder = {
            Text(text = placeholder, fontSize = 16.sp)
        },
        label = { Text(text = label) },
        maxLines = 2,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (event == "phone") KeyboardType.Number else KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
    )
}

