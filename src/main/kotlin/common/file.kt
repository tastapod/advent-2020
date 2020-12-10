package common

import java.io.File

fun file(path: String) = File(ClassLoader.getSystemResource(path).file)
