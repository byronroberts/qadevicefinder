package roberts.byron.qadevicefinder.domain

const val AVAILABLE = "Available"

data class Device(var imageUrl: String? = "",
                  var manufacturer: String? = "",
                  var model: String? = "",
                  var operatingSystem: OperatingSystem? = OperatingSystem.NONE,
                  var osVersion: String? = "",
                  var lenderName: String? = AVAILABLE,
                  var dateLentOut: String? = "")

