package com.medidata.tdd

case class TotalExpenditure() {
  var spent = BigDecimal(0)

  def append(spent: SpendAmount): Unit = {
    this.spent = this.spent + spent.value
  }
}
