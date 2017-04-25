package com.medidata.tdd


sealed trait SpendResult

case object SpendExceeded extends SpendResult

case object SpendAdded extends SpendResult
