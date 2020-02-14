package roberts.byron.qadevicefinder.ui.devices

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import roberts.byron.qadevicefinder.R
import roberts.byron.qadevicefinder.domain.Device

class DevicesRecyclerViewAdapter() : RecyclerView.Adapter<DeviceViewHolder>() {

    var devices: List<Device?> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_device, parent, false)
        return DeviceViewHolder(view)
    }

    override fun getItemCount() = devices.size

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = devices[position]
        holder.deviceManufacturer.text = device?.manufacturer
        holder.deviceModel.text = device?.model
        holder.operatingSystem.text = device?.operatingSystem?.name
        holder.osVersionNumber.text = device?.osVersion
    }
}

class DeviceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val barcode = view.findViewById<TextView>(R.id.barcode)
    val deviceManufacturer = view.findViewById<TextView>(R.id.deviceManufacturer)
    val deviceModel = view.findViewById<TextView>(R.id.deviceModel)
    val operatingSystem = view.findViewById<TextView>(R.id.operatingSystem)
    val osVersionNumber = view.findViewById<TextView>(R.id.osVersionNumber)
}