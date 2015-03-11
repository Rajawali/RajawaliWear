package rajawali.wear;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import rajawali.renderer.RajawaliRenderer;
import rajawali.util.egl.RajawaliEGLConfigChooser;

public class RajawaliWearActivity extends Activity {

    private GLSurfaceView glSurfaceView;
    private RajawaliRenderer rajawaliRenderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        glSurfaceView = new GLSurfaceView(this);
        glSurfaceView.setEGLContextClientVersion(2);

        if (isMultiSamplingEnabled()) {
            glSurfaceView.setEGLConfigChooser(createEglConfigChooser());
        }

        setContentView(glSurfaceView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (rajawaliRenderer != null) {
            glSurfaceView.setRenderMode(getRenderMode());
            glSurfaceView.onResume();
            rajawaliRenderer.onVisibilityChanged(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (rajawaliRenderer != null) {
            glSurfaceView.onPause();
            rajawaliRenderer.onVisibilityChanged(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        rajawaliRenderer.onSurfaceDestroyed();
    }

    /**
     * The render mode to apply to the SurfaceView.
     *
     * @return the render mode
     * @see android.opengl.GLSurfaceView#RENDERMODE_CONTINUOUSLY
     * @see android.opengl.GLSurfaceView#RENDERMODE_WHEN_DIRTY
     */
    protected int getRenderMode() {
        return GLSurfaceView.RENDERMODE_WHEN_DIRTY;
    }

    /**
     * Get the renderer currently set to the SurfaceView.
     *
     * @return the set renderer or null.
     */
    protected RajawaliRenderer getRenderer() {
        return rajawaliRenderer;
    }

    /**
     * Set the renderer for the SurfaceView.
     *
     * @param rajawaliRenderer the renderer for the scene
     */
    protected void setRenderer(RajawaliRenderer rajawaliRenderer) {
        this.rajawaliRenderer = rajawaliRenderer;
        glSurfaceView.setRenderer(rajawaliRenderer);
        rajawaliRenderer.setSurfaceView(glSurfaceView);
    }

    /**
     * The GLES version to apply to the SurfaceView.
     *
     * @return a valid GLES Version of 2 or greater.
     */
    protected int getTargetGLVersion() {
        return 2;
    }

    /**
     * Flag for multi-sampling.
     *
     * @return true to enable multi-sampling
     */
    protected boolean isMultiSamplingEnabled() {
        return false;
    }

    /**
     * The config chooser to apply to the SurfaceView when multi-sampling is enabled.
     *
     * @return a config chooser implementation
     */
    protected GLSurfaceView.EGLConfigChooser createEglConfigChooser() {
        return new RajawaliEGLConfigChooser();
    }

}
