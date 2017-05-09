package com.medidata.tdd.domain

case class AvailableExpenditure(value: BigDecimal) {
  def <(spentAmount: SpendAmount): Boolean = {
    value < spentAmount.value
  }
}

object AvailableExpenditure {
  def apply(availableToSpend: String) = new AvailableExpenditure(BigDecimal(availableToSpend))
}
