package de.wulkanat.www.nativewindowslauncher

import android.opengl.GLSurfaceView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.RelativeLayout

class MainActivity : AppCompatActivity() {
    lateinit var glSurfaceView: GLSurf

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        super.onCreate(savedInstanceState)
        //Making Status- and Navbar fully transparent
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        glSurfaceView = GLSurf(this, getStatusBarHeightPixles(), getNavBarHeightPixels())
        setContentView(R.layout.activity_main)

        val mainLayout = findViewById<RelativeLayout>(R.id.mainLayout)
        val glParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
        mainLayout.addView(glSurfaceView, glParams)
    }

    override fun onBackPressed() {
        glSurfaceView.mRenderer.onBackPressed()
    }

    fun getStatusBarHeightPixles(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    fun getNavBarHeightPixels(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    override fun onPause() {
        super.onPause()
        glSurfaceView.onPause()
    }

    override fun onResume() {
        super.onResume()
        glSurfaceView.onResume()
    }
}
