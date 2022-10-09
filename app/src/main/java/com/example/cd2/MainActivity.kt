package com.example.cd2

import android.Manifest
import android.app.NotificationManager
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.*
import android.content.pm.PackageManager
import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener


class MainActivity : AppCompatActivity() {

//    var volumeBroadcastReceiver = object: BroadcastReceiver(){
//        override fun onReceive(p0: Context?, p1: Intent?) {
//            var action = intent?.getAction()
//
//            if (action != null) {
//                if(action.equals("android.media.VOLUME_CHANGED_ACTION")){
//                    System.out.println("volume changed")
//                }
//
//            }
//        }
//    }

    var speedRate:Float = 1.0F


    //블루투스 리시버
    //http://jinyongjeong.github.io/2018/09/27/bluetoothpairing/
    //https://jung-max.github.io/2019/08/27/Android-Bluetooth/
    private val bluetoothBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {
            val bleOnOffBtn:ToggleButton = findViewById(R.id.bluetooth_on_off_btn)

            var action = intent?.action //입력된 action

            if(action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                var state =
                    intent?.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
                if (state == BluetoothAdapter.STATE_OFF) {
                    System.out.println("블루투스 off")
                    bleOnOffBtn.isChecked = false

                }else if  (state == BluetoothAdapter.STATE_ON){
                    System.out.println("블루투스 on")
                    bleOnOffBtn.isChecked = true
                }
            }
            if(BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)){
                val device: BluetoothDevice? = intent?.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE) //연결된 장치

                bleOnOffBtn.isChecked = true
                var bleDeviceTextView : TextView= findViewById(R.id.connectedDevice)
                if (ActivityCompat.checkSelfPermission(
                        this@MainActivity,
                        Manifest.permission.BLUETOOTH_CONNECT
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //       ActivityCompat#requestPermissions
                    ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.BLUETOOTH_CONNECT),1)
                    return
                }
                if (device != null) {
                    bleDeviceTextView.text = device.name
                    //bleDeviceTextView.text = device.name

                    Toast.makeText(
                        baseContext,
                        "Device is now Connected",
                        Toast.LENGTH_SHORT
                    ).show()
                }else{

                    Toast.makeText(
                        baseContext,
                        "No Device is Connected",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }

                //Device와 TTS audio 연결
                device.createBond()

            }else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
                bleOnOffBtn.isChecked = false
                var bleDeviceTextView : TextView= findViewById(R.id.connectedDevice)

                bleDeviceTextView.text = ""

                System.out.println("해제")
                Toast.makeText(
                    baseContext,
                    "Device is disconnected",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val audioManager = this.applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val volumeSeekBar:SeekBar = findViewById(R.id.volumeSeeBar)
        var currentVolume =audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)  //현재 음량
        var maxVolume =audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)

        volumeSeekBar.max =maxVolume
        volumeSeekBar.progress = currentVolume

        volumeSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })


        //다른 어플이나 application에서 broadcast하는 메시지를 받을 수 있도록 설정
        val filter = IntentFilter().apply{
            addAction(BluetoothAdapter.ACTION_STATE_CHANGED)//블루투스상태변화액션
            addAction(BluetoothDevice.ACTION_ACL_CONNECTED)//연결확인
            addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED)//
            addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED)//연결끊김확인
        }
        registerReceiver(bluetoothBroadcastReceiver,filter)//reiver와 filter연결

        //https://www.geeksforgeeks.org/spinner-in-kotlin/
        initSpeedRateSpinner()


    }

    private fun initSpeedRateSpinner(){
        val speedList:Array<Float> = arrayOf(1.0F,2.0F,3.0F)
        val speedRateSpinner = findViewById<Spinner>(R.id.spinner)
        val speedAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,speedList)
        speedRateSpinner.adapter=speedAdapter

        //https://jeongupark-study-house.tistory.com/11
        speedRateSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                //set speedRate of TTS
                when(position){
                    0->speedRate = 1.0F
                    1->speedRate = 1.5F
                    2->speedRate = 2.0F
                }

                //TODO: send speedRate to NotificationListener
//                val intent = Intent(android.provider.Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
//                intent.putExtra("SpeedRate",speedRate)
//                startActivity(intent)

                //show user speedRate
                Toast.makeText(this@MainActivity,
                    "speed set : $speedRate",Toast.LENGTH_SHORT).show()

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        val audioManager = this.applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
//        val volumeSeekBar:SeekBar = findViewById(R.id.volumeSeeBar)
//        var currentVolume =audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)  //현재 음량
//
//        when (keyCode) {
//            KeyEvent.KEYCODE_VOLUME_DOWN,KeyEvent.KEYCODE_VOLUME_UP -> {
//                volumeSeekBar.setProgress(currentVolume)
//            }
//        }
//        return super.onKeyDown(keyCode, event)
//    }


    //TODO: 한번켜지면 꺼지지 않음
    fun isBluetoothEnabled(): Boolean {
        val myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        return myBluetoothAdapter.isEnabled
    }

    //블루투스 연결 설정 버튼 클릭 시
    fun onClickBluetoothBtn(view: View) {
        val intent = Intent(Settings.ACTION_BLUETOOTH_SETTINGS)
        startActivity(intent) // 블루투스 연결 설정 화면으로 이동
    }

    //TODO: 한번켜지면 꺼지지 않음
    //알림 설정 버튼 클릭 시
    fun onClickNotiSettingBtn(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName) //해당 앱 알림 설정 화면으로 이동
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
//                Log.d("태그" val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
            }
        }
    }


    override fun onStart() {
        val bleOnOffBtn:ToggleButton = findViewById(R.id.bluetooth_on_off_btn)

        super.onStart()

        val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //https://kobbi-reply.tistory.com/17
        if(Build.VERSION.SDK_INT >= 23){
            if(!notificationManager.isNotificationPolicyAccessGranted){ //알림 접근 권한이 허용 여부 확인
                val intent = Intent(android.provider.Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
                this.startActivity(intent)
            }else{
                //request permission 은 오직 한 번만
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_DENIED||ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
                    // Request the user to grant permission to read SMS messages
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_SMS,Manifest.permission.BLUETOOTH_CONNECT), 2)
                }

            }
        }

        //블루투스 켜짐/꺼짐에 따라 버튼 on/off
        /*if(!isBluetoothEnabled()){
            bleOnOffBtn.isChecked = false
        }else{
            bleOnOffBtn.isChecked = true
        }*/
        bleOnOffBtn.isChecked = isBluetoothEnabled()

        //초기 볼륨
        val audioManager = this.applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val volumeSeekBar:SeekBar = findViewById(R.id.volumeSeeBar)
        var currentVolume =audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)  //현재 음량
        var maxVolume =audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)

        volumeSeekBar.max =maxVolume
        volumeSeekBar.progress = currentVolume

        //TODO: 핸드폰 옆의 볼륨을 높힐때 바로 media 볼륨이 잡히지 않는다


    }

}