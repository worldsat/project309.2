package com.makancompany.project309.ui.customizer.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.makancompany.project309.R
import com.makancompany.project309.ui.theme.CaramelSecondary
import com.makancompany.project309.ui.theme.EspressoPrimary
import com.makancompany.project309.ui.theme.SandOnSurface
import com.makancompany.project309.ui.theme.SandOnSurfaceVariant
import com.makancompany.project309.ui.theme.SandSurfaceContainer

@Composable
fun BaristaNotesSection(
    notes: String,
    onNotesChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        // Label
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_stylus_note),
                contentDescription = null,
                tint = CaramelSecondary,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.size(6.dp))
            Text(
                text = "Barista Notes",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = EspressoPrimary
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Input Container
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(SandSurfaceContainer)
                .padding(14.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    contentAlignment = Alignment.TopStart
                ) {
                    if (notes.isEmpty()) {
                        Text(
                            text = "e.g. Extra hot, light caramel drizzle, extra napkin...",
                            style = MaterialTheme.typography.bodyMedium,
                            color = SandOnSurfaceVariant.copy(alpha = 0.65f)
                        )
                    }

                    BasicTextField(
                        value = notes,
                        onValueChange = onNotesChange,
                        textStyle = MaterialTheme.typography.bodyMedium.copy(
                            color = SandOnSurface
                        ),
                        cursorBrush = SolidColor(CaramelSecondary),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Optional hint tag at bottom-right
                Text(
                    text = "Optional",
                    style = MaterialTheme.typography.labelSmall,
                    color = SandOnSurfaceVariant,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}
