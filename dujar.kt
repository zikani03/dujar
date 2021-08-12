import java.util.jar.JarFile
import java.util.jar.JarEntry
import java.io.File

// based on: https://stackoverflow.com/a/59234917
fun humanize(bytes: Long) = when {
    bytes == Long.MIN_VALUE || bytes < 0 -> "N/A"
    bytes < 1024L -> "$bytes B"
    bytes <= 0xfffccccccccccccL shr 40 -> "%.1f KiB".format(bytes.toDouble() / (0x1 shl 10))
    bytes <= 0xfffccccccccccccL shr 30 -> "%.1f MiB".format(bytes.toDouble() / (0x1 shl 20))
    bytes <= 0xfffccccccccccccL shr 20 -> "%.1f GiB".format(bytes.toDouble() / (0x1 shl 30))
    bytes <= 0xfffccccccccccccL shr 10 -> "%.1f TiB".format(bytes.toDouble() / (0x1 shl 40))
    bytes <= 0xfffccccccccccccL -> "%.1f PiB".format((bytes shr 10).toDouble() / (0x1 shl 40))
    else -> "%.1f EiB".format((bytes shr 20).toDouble() / (0x1 shl 40))
}

fun normalizeClass(name: String): String {
    var finalName = name
    if (name.endsWith(".class"))
        finalName = name.substring(0, name.length - 6)
    return finalName.replace('$', '/').replace('/', '.')
}

fun main(args: Array<String>) {
    val file = File(args[0])
    var entries = mutableListOf<JarEntry>()
    
    JarFile(file).stream().forEach { it -> entries.add(it) }

    entries.sortedByDescending({ it -> it.getSize() }).forEach { entry -> 
        println("${humanize(entry.getSize())}\t${normalizeClass(entry.getName())}")
    }
}