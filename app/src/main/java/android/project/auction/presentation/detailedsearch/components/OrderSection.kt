package android.project.auction.presentation.detailedsearch.components

import android.project.auction.domain.model.util.ItemOrder
import android.project.auction.domain.model.util.OrderType
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    itemOrder: ItemOrder = ItemOrder.Created(OrderType.Descending),
    onOrderChange: (ItemOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Created Date",
                selected = itemOrder is ItemOrder.Created,
                onSelect = { onOrderChange(ItemOrder.Created(itemOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Starting Price",
                selected = itemOrder is ItemOrder.StartingPrice,
                onSelect = { onOrderChange(ItemOrder.StartingPrice(itemOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))

        }
        Spacer(modifier = Modifier.height(0.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = itemOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(itemOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = itemOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(itemOrder.copy(OrderType.Descending))
                }
            )
        }
    }
}