package org.d3if3015.aplikasifajar.ui.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3015.aplikasifajar.R
import org.d3if3015.aplikasifajar.model.Mobil
import org.d3if3015.aplikasifajar.navigation.Screen
import org.d3if3015.aplikasifajar.ui.theme.AplikasiFajarTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  MainScreen(navController: NavHostController){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.About.route)
                        }) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = stringResource(id = R.string.tentang_aplikasi),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ){padding ->
        ScreenContent(Modifier.padding(padding))
    }
}

@Composable
fun GaleriMobil(mobil: Mobil){
    Column(
        modifier = Modifier
            .padding(16.dp)
    )
    {
        Image(
            painter = painterResource(id = mobil.imageResId),
            contentDescription = stringResource(id = R.string.gambar, mobil.nama),
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(150.dp)
        )

    }
}

@SuppressLint("StringFormatInvalid")
@Composable
fun ScreenContent(modifier: Modifier) {
    val mobil1 = "Lamborghini Aventador"
    val mobil2 = "Lamborghini Urus"
    val mobil3 = "Lamborghini Veneno"
    var nominal by rememberSaveable { mutableStateOf("") }
    var nominal2 by rememberSaveable { mutableStateOf("") }
    var nominal3 by rememberSaveable { mutableStateOf("") }
    var harga1 by rememberSaveable {
        mutableFloatStateOf(0f)
    }
    var harga2 by rememberSaveable {
        mutableFloatStateOf(0f)
    }
    var harga3 by rememberSaveable {
        mutableFloatStateOf(0f)
    }
    harga1 = 1500000F
    harga2 = 1000000F
    harga3 = 1900000F

    var hasil by rememberSaveable {
        mutableFloatStateOf(0f)
    }
    var hasil2 by rememberSaveable {
        mutableFloatStateOf(0f)
    }
    var hasil3 by rememberSaveable {
        mutableFloatStateOf(0f)
    }
    var kategori by rememberSaveable {
        mutableIntStateOf(0)
    }
    var nominal1Error by rememberSaveable {
        mutableStateOf(false)
    }
    var nominal2Error by rememberSaveable {
        mutableStateOf(false)
    }
    var nominal3Error by rememberSaveable {
        mutableStateOf(false)
    }
    val radioOptions = listOf(
        stringResource(id = R.string.bulan),
        stringResource(id = R.string.tahun)
    )
    var waktu1 by rememberSaveable { mutableStateOf(radioOptions[0]) }
    var waktu2 by rememberSaveable { mutableStateOf(radioOptions[0]) }
    var waktu3 by rememberSaveable { mutableStateOf(radioOptions[0]) }

    val context = LocalContext.current


    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Row (
            modifier = Modifier
                .fillMaxSize()
                .absolutePadding(left = 8.dp, top = 16.dp, right = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = "1. Lamborghini Aventador", fontWeight = FontWeight.Bold)
            Text(text = "Rp.1.500.000")
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            GaleriMobil(mobil = Mobil("lamborghini", R.drawable.lamborghini))
            Column {
                OutlinedTextField(
                    value = nominal,
                    onValueChange = { nominal = it},
                    label = { Text(text = stringResource(id = R.string.nominal))},
                    isError = nominal1Error,
                    trailingIcon = { IconPicker(nominal1Error, "")},
                    supportingText = { ErrorHint(nominal1Error)},
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .absolutePadding(top = 5.dp, right = 10.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Row {
                    Column (
                        modifier = Modifier
                            .padding(top = 6.dp)
                            .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                    ){
                        radioOptions.forEach {text ->
                            WaktuOptions(
                                label = text,
                                isSelected = waktu1 == text,
                                modifier = Modifier
                                    .selectable(
                                        selected = waktu1 == text,
                                        onClick = { waktu1 = text },
                                        role = Role.RadioButton
                                    )
                                    .absolutePadding(right = 8.dp, left = 8.dp)
                                    .height(35.dp)
                            )
                        }
                    }
                }
            }
        }
        Row (
            modifier = Modifier.fillMaxWidth()
                .absolutePadding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Absolute.Center
        ){
            if (hasil != 0f){
                Text(text = stringResource(id = R.string.hasil, hasil),
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.absolutePadding(left = 9.dp))
                Text(
                    modifier = Modifier.padding(vertical = 6.dp),
                    text = stringResource(kategori),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
        Row {
            Button(onClick = {
                if (nominal.isBlank() || nominal == "0" || nominal.toFloat()==0f) {
                    nominal1Error = true
                    return@Button
                }
                nominal1Error = false
                hasil = hitungMobil(nominal.toFloat(), harga1)
                kategori = getKategori(waktu1 == radioOptions[0])

            },
                modifier = Modifier.absolutePadding(left = 16.dp, bottom = 16.dp),
                contentPadding = PaddingValues(horizontal = 55.dp, vertical = 17.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = stringResource(id = R.string.hitung))
            }
            Spacer(modifier = Modifier.absolutePadding(left = 16.dp))
            if (hasil != 0f) {
                Button(
                    onClick = {
                        shareData(
                            context = context,
                            message = context.resources.getString(R.string.template_bagikan, mobil1,harga1.toString(), nominal, waktu1,hasil.toString(), context.getString(kategori))
                        )
                    },
                    modifier = Modifier.absolutePadding(left = 16.dp, bottom = 16.dp),
                    contentPadding = PaddingValues(horizontal = 55.dp, vertical = 17.dp),
                    shape = RoundedCornerShape(10.dp)
                )
                {
                    Text(text = stringResource(id = R.string.bagikan_hasil))
                }
            }
            }


        Divider()

        Row (
            modifier = Modifier
                .fillMaxSize()
                .absolutePadding(left = 8.dp, top = 16.dp, right = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = "2. Lamborghini Urus", fontWeight = FontWeight.Bold)
            Text(text = "Rp.1.000.000")
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            GaleriMobil(mobil = Mobil("lamborghini", R.drawable.urus))
            Column {
                OutlinedTextField(
                    value = nominal2,
                    onValueChange = { nominal2 = it},
                    label = { Text(text = stringResource(id = R.string.nominal))},
                    isError = nominal2Error,
                    trailingIcon = { IconPicker(nominal2Error, "")},
                    supportingText = { ErrorHint(nominal2Error)},
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .absolutePadding(top = 5.dp, right = 10.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Row {
                    Column (
                        modifier = Modifier
                            .padding(top = 6.dp)
                            .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                    ){
                        radioOptions.forEach {text ->
                            WaktuOptions(
                                label = text,
                                isSelected = waktu2 == text,
                                modifier = Modifier
                                    .selectable(
                                        selected = waktu2 == text,
                                        onClick = { waktu2 = text },
                                        role = Role.RadioButton
                                    )
                                    .absolutePadding(right = 8.dp, left = 8.dp)
                                    .height(35.dp)
                            )
                        }
                    }
                }
            }
        }
        Row (
            modifier = Modifier.fillMaxWidth()
                .absolutePadding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Absolute.Center
        ){
            if (hasil2 != 0f){
                Text(text = stringResource(id = R.string.hasil, hasil2),
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.absolutePadding(left = 9.dp))
                Text(
                    modifier = Modifier.padding(vertical = 6.dp),
                    text = stringResource(kategori),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
        Row {
            Button(onClick = {
                if (nominal2.isBlank() || nominal2 == "0" || nominal2.toFloat()==0f) {
                    nominal2Error = true
                    return@Button
                }

                nominal2Error = false
                hasil2 = hitungMobil(nominal2.toFloat(), harga2)
                kategori = getKategori(waktu2 == radioOptions[0])

            },
                modifier = Modifier.absolutePadding(left = 16.dp, bottom = 16.dp),
                contentPadding = PaddingValues(horizontal = 55.dp, vertical = 17.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = stringResource(id = R.string.hitung))
            }
            Spacer(modifier = Modifier.absolutePadding(left = 16.dp))
            if (hasil2 != 0f) {
            Button(
                onClick = {
                    shareData(
                        context = context,
                        message = context.resources.getString(R.string.template_bagikan, mobil2, harga2.toString(), nominal2,waktu2,
                            hasil2.toString(), context.getString(kategori))
                    )
                },
                modifier = Modifier.absolutePadding(left = 16.dp, bottom = 16.dp),
                contentPadding = PaddingValues(horizontal = 55.dp, vertical = 17.dp),
                shape = RoundedCornerShape(10.dp)
            )
            {
                Text(text = stringResource(id = R.string.bagikan_hasil))
                }
            }
        }

        Divider()
        Row (
            modifier = Modifier
                .fillMaxSize()
                .absolutePadding(left = 8.dp, top = 16.dp, right = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = "3. Lamborghini Veneno", fontWeight = FontWeight.Bold)
            Text(text = "Rp.1.900.000")
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            GaleriMobil(mobil = Mobil("lamborghini", R.drawable.veneno))
            Column {
                OutlinedTextField(
                    value = nominal3,
                    onValueChange = { nominal3 = it},
                    label = { Text(text = stringResource(id = R.string.nominal))},
                    isError = nominal3Error,
                    trailingIcon = { IconPicker(nominal3Error, "")},
                    supportingText = { ErrorHint(nominal3Error)},
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .absolutePadding(top = 5.dp, right = 10.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Row {
                    Column (
                        modifier = Modifier
                            .padding(top = 6.dp)
                            .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                    ){
                        radioOptions.forEach {text ->
                            WaktuOptions(
                                label = text,
                                isSelected = waktu3 == text,
                                modifier = Modifier
                                    .selectable(
                                        selected = waktu3 == text,
                                        onClick = { waktu3 = text },
                                        role = Role.RadioButton
                                    )
                                    .absolutePadding(right = 8.dp, left = 8.dp)
                                    .height(35.dp)
                            )
                        }
                    }
                }
            }
        }
        Row (
            modifier = Modifier.fillMaxWidth()
                .absolutePadding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Absolute.Center
        ){
            if (hasil3 != 0f){
                Text(text = stringResource(id = R.string.hasil, hasil3),
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.absolutePadding(left = 9.dp))
                Text(
                    modifier = Modifier.padding(vertical = 6.dp),
                    text = stringResource(kategori),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
        Row {
            Button(onClick = {
                if (nominal3.isBlank() || nominal3 == "0" || nominal3.toFloat()==0f) {
                    nominal3Error = true
                    return@Button
                }

                nominal3Error = false
                hasil3 = hitungMobil(nominal3.toFloat(), harga3)
                kategori = getKategori(waktu3 == radioOptions[0])

            },
                modifier = Modifier.absolutePadding(left = 16.dp, bottom = 16.dp),
                contentPadding = PaddingValues(horizontal = 55.dp, vertical = 17.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = stringResource(id = R.string.hitung))
            }
            Spacer(modifier = Modifier.absolutePadding(left = 16.dp))
            if (hasil3 != 0f) {
                Button(
                    onClick = {
                        shareData(
                            context = context,
                            message = context.resources.getString(R.string.template_bagikan, mobil3, harga3.toString(), nominal3,waktu3, hasil3.toString(), context.getString(kategori))
                        )
                    },
                    modifier = Modifier.absolutePadding(left = 16.dp, bottom = 16.dp),
                    contentPadding = PaddingValues(horizontal = 55.dp, vertical = 17.dp),
                    shape = RoundedCornerShape(10.dp)
                )
                {
                    Text(text = stringResource(id = R.string.bagikan_hasil))
                }
            }
        }
        Spacer(modifier = Modifier.height(250.dp))
    }
}

private fun shareData(context: Context, message: String){
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type =  "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null){
        context.startActivity(shareIntent)
    }
}

@Composable
fun ErrorHint(isError: Boolean){
    if (isError){
        Text(text = stringResource(id = R.string.input_invalid))
    }
}

@Composable
fun IconPicker(isError: Boolean, unit: String){
    if (isError){
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    }else{
        Text(text = unit)
    }
}


private fun hitungMobil(nominal:Float, harga:Float): Float{
    return harga / nominal
}

private fun getKategori(waktu: Boolean): Int{
    return if (waktu){
        R.string.hasil_bulan
    }else{
        R.string.hasil_tahun
    }
}

@Composable
fun WaktuOptions(label: String, isSelected: Boolean,modifier: Modifier){
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        RadioButton(selected = isSelected, onClick = null)
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun GreetingPreview() {
    AplikasiFajarTheme {
        MainScreen(rememberNavController())
    }
    GaleriMobil(Mobil("lamborghini",R.drawable.lamborghini))
}