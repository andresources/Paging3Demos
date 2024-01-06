package com.paging.ex3.data.response

import com.squareup.moshi.Json

data class ApiResponse(val data: List<Data>, val page: Int, val per_page: Int, val total: Int, val total_pages: Int)