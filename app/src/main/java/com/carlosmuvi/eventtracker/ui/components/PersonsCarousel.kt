package com.carlosmuvi.eventtracker.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carlosmuvi.eventtracker.data.Person

@Composable
fun PersonsCarousel(persons: List<Person>) {
    Box(Modifier.wrapContentSize()) {
        persons.forEachIndexed { index, person ->
            PersonAvatar(
                person = person, Modifier
                    .size(100.dp)
                    .offset(x = (45 * index).dp)
            )
        }
    }
}

@Composable
fun PersonAvatar(person: Person, modifier: Modifier) {
    Box(
        modifier
    ) {
        Canvas(
            modifier = Modifier.matchParentSize(),
            onDraw = { drawCircle(color = Color.LightGray) }
        )
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = person.name.first().uppercaseChar().toString()
        )
    }
}

@Preview
@Composable
fun PersonsCarouselPreview() {
    PersonsCarousel(
        persons = listOf(
            Person("Carlos"),
            Person("Mario"),
            Person("Alfredo"),
        )
    )
}