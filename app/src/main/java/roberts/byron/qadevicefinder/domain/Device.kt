package roberts.byron.qadevicefinder.domain

data class Device(var deviceId: String, var imageUrl: String, var deviceName: String, var operatingSystem: OperatingSystem, val osVersion: String, val lenderName: String, val dateLentOut: String)

enum class OperatingSystem {
    ANDROID, IOS;
}