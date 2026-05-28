package com.practicum.firstcomposeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactDetails(Contact(
                name = "Василий",
                surname = "Аркадьевич",
                familyName = "Иванов",
                imageRes = null,
                isFavorite = true,
                phone = "+7 495 865 85 11",
                address = "г. Москва, ул. Краснознаменная, д. 15, кв. 43",
                email = "king22@yandex.ru"
            ))
        }
    }
}

//Первая функция предпросмотра
@Preview(name = "portrait", showSystemUi = true)
@Composable
fun ContactDetailsPreviewFirst() {
    ContactDetails(Contact(
        name = "Василий",
        surname = "Аркадьевич",
        familyName = "Иванов",
        imageRes = null,
        isFavorite = true,
        phone = "+7 495 865 85 11",
        address = "г. Москва, ул. Краснознаменная, д. 15, кв. 43",
        email = "king22@yandex.ru"
    ))
}
//Вторая функция предпросмотра
@Preview(name = "portrait", showSystemUi = true)
@Composable
fun ContactDetailsPreviewSecond() {
    ContactDetails(Contact(
        name = "Василий",
        surname = null,
        familyName = "Иванов",
        imageRes = R.drawable.man,
        isFavorite = false,
        phone = "---",
        address = "г. Москва, ул. Краснознаменная, д. 15, кв. 43",
        email = null
    ))
}

@Composable
fun ContactDetails(contact: Contact) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageDraw(
            contact.imageRes,
            contact.name,
            contact.familyName
        )
        FullNameDraw(
            contact.name,
            contact.familyName,
            contact.surname,
            contact.isFavorite
        )
        InfoRow(
            textResource = R.string.phone,
            textValue = contact.phone
        )
        InfoRow(
            textResource = R.string.address,
            textValue = contact.address
        )
        InfoRow(
            textResource = R.string.email,
            textValue = contact.email
        )
    }
}

@Composable
fun ImageDraw(imageRes: Int?, name: String, familyName: String) {
    if (imageRes != null) {
        Image(
            modifier = Modifier.size(80.dp, 80.dp)
                .padding(top = 15.dp, bottom = 18.dp),
            alignment = Alignment.Center,
            painter = painterResource(id = imageRes),
            contentDescription = null
        )
    } else {
        Box(
            modifier = Modifier.size(80.dp, 80.dp)
                .padding(top = 15.dp, bottom = 18.dp),
            contentAlignment = Alignment.Center
        ){
            Icon(
                painter = painterResource(id = R.drawable.circle),
                tint = Color.LightGray,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            Text(name.take(1) + familyName.take(1))
        }
    }
}

@Composable
fun FullNameDraw(name: String, familyName: String, surname: String?, isFavorite: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 50.dp)
    ) {
        Text(
            text = "$name ${surname.orEmpty()}",
            style = TextStyle(
                fontSize = 22.sp
            )
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "$familyName ",
                style = TextStyle(
                    fontSize = 25.sp
                )
            )
            if (isFavorite) Image(
                painter = painterResource(id = android.R.drawable.star_big_on),
                contentDescription = null,
            )
        }
    }
}

@Composable
fun InfoRow(textResource: Int, textValue: String?) {
    if (textValue != null)
    Row(
        modifier = Modifier.padding(vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(textResource) + ": ",
            style = TextStyle(
                fontStyle = FontStyle.Italic,
                fontSize = 18.sp
            ),
            modifier = Modifier.weight(0.5f),
            textAlign = TextAlign.End
        )
        Text(
            text = textValue,
            style = TextStyle(
                fontSize = 16.sp
            ),
            modifier = Modifier.weight(0.5f),
            textAlign = TextAlign.Start
        )
    }

}

data class Contact(
    val name: String,                   //Имя
    val surname: String? = null,        //Отчество
    val familyName: String,             //Фамилия
    val imageRes: Int? = null,          //Ресурс фотографии
    val isFavorite: Boolean = false,    //Признак избранного контакта
    val phone: String,                  //Телефон
    val address: String,                //Адрес
    val email: String? = null,          //E-mail
)
