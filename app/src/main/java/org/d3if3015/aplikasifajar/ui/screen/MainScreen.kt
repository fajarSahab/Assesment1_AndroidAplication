package org.d3if3015.aplikasifajar.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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

@Composable
fun ScreenContent(modifier: Modifier) {
    var nominal by rememberSaveable { mutableStateOf(" ") }
    var harga1 by rememberSaveable {
        mutableFloatStateOf(0f)
    }
    harga1 = 1500000F
    var hasil by rememberSaveable {
        mutableFloatStateOf(0f)
    }
    var kategori by rememberSaveable {
        mutableIntStateOf(0)
    }
    var nominalError by rememberSaveable {
        mutableStateOf(false)
    }
    val radioOptions = listOf(
        stringResource(id = R.string.bulan),
        stringResource(id = R.string.tahun)
    )
    var waktu by remember { mutableStateOf(radioOptions[0]) }


    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)
    ) {
        Row {
                Spacer(modifier = Modifier.padding(horizontal = 3.dp))
            Text(text = "1. Lamborghini Veneno", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(horizontal = 50.dp))
            Text(text = "$1.500.000")
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
                                isSelected = waktu == text,
                                modifier = Modifier
                                    .selectable(
                                        selected = waktu == text,
                                        onClick = { waktu = text },
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
        Row {
            Button(onClick = {
                hasil = hitungMobil(nominal.toFloat(), harga1)
                kategori = getKategori(waktu == radioOptions[0])

            },
                modifier = Modifier.absolutePadding(left = 16.dp, bottom = 16.dp),
                contentPadding = PaddingValues(horizontal = 55.dp, vertical = 17.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = stringResource(id = R.string.hitung))
            }
            Spacer(modifier = Modifier.absolutePadding(left = 16.dp))
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
        Divider()
        Row {
            Spacer(modifier = Modifier.padding(horizontal = 3.dp))
            Text(text = "2. Lamborghini Huracan", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(horizontal = 23.dp))
            Text(text = "Rp.1.000.000.000")
        }
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