package com.example.btvn2_lttbdd_b4.Screens

import android.R
import android.R.attr.contentDescription
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBarDefaults.colors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Page1(){
    var email by remember { mutableStateOf("") }

    var isChecked by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ){
        Text(
            text = "Hệ thống\n"+"Quản lý Thư viện",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Sinh viên",
            fontWeight = FontWeight.Bold)
        Row(

            modifier = Modifier
                .padding(vertical = 5.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            OutlinedTextField(
                modifier = Modifier
                    .weight(1f),
                value = email,
                onValueChange = {
                    email = it
                },
                maxLines = 1,
                label = {Text(text = "Họ và tên")},
                placeholder = { Text("Nhập họ và tên") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Icon o dau") },
            )


            Button(
                onClick = {},
                shape = RoundedCornerShape(5.dp),
                colors= ButtonDefaults.buttonColors(
                    containerColor = Color.Blue
                )
                ) {
                Text(
                    text = "Thay đổi",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
            }

        }
        Text(text = "Danh sách sách",
            fontWeight = FontWeight.Bold)
        Box(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .padding(20.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.LightGray),

        ){
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top=25.dp)

            ){
                items(3){

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.selectable(
                                selected = isChecked,
                                onClick = { isChecked=!isChecked },
                                role = Role.Checkbox
                            )
                                .padding(vertical = 5.dp)
                                .clip(RoundedCornerShape(25.dp))
                                .fillMaxWidth()
                                .background(Color.White)
                        ){
                            Checkbox(
                                checked = isChecked,
                                onCheckedChange = null,
                                colors = CheckboxDefaults.colors(
                                    checkedColor = Color.Green,
                                    uncheckedColor = Color.Black,
                                    disabledCheckedColor = Color.Gray
                                ),
                                modifier = Modifier.padding(start =  20.dp)

                            )
                            Text(text="Sach 1", Modifier.padding(16.dp),color=Color.Black)
                        }

                }

            }


        }

        Button(
            onClick = {},
            modifier = Modifier
                .padding(vertical = 10.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue
            )

            ) {
            Text(
                text="Thêm sách",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,)

        }

        //Dung chiem khong gian giua 2 item
        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier= Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(){
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        Icons.Default.Home,
                        contentDescription = "Icon o cuoi",
                        tint = Color.Blue

                    )
                }
            }
            Column(){
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        Icons.Default.List,
                        contentDescription = "Icon o cuoi"
                    )
                }
            }
            Column(){
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "Icon o cuoi"
                    )
                }
            }

        }
    }
}