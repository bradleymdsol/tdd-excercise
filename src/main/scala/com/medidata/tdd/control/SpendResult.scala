package com.medidata.tdd.control

sealed trait SpendResult

case object SpendExceeded extends SpendResult

case object SpendAdded extends SpendResult
