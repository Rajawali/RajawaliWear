package rajawali.wear;

import android.support.wearable.watchface.Gles2WatchFaceService;
import android.support.wearable.watchface.WatchFaceStyle;
import android.view.SurfaceHolder;

import javax.microedition.khronos.opengles.GL10;

public abstract class RajawaliWatchFaceService extends Gles2WatchFaceService {

    @Override
    public final Engine onCreateEngine() {
        return getRajawaliWatchEngine();
    }

    protected abstract Engine getRajawaliWatchEngine();

    protected abstract class RajawaliWatchEngine extends Gles2WatchFaceService.Engine {

        private final GL10 gl10 = new EmptyGL10();
        private RajawaliWatchRenderer renderer;

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);

            setWatchFaceStyle(getWatchFaceStyle());
        }

        protected abstract WatchFaceStyle getWatchFaceStyle();

        protected abstract RajawaliWatchRenderer getRenderer();

        @Override
        public void onGlContextCreated() {
            super.onGlContextCreated();

            renderer = getRenderer();
        }

        @Override
        public void onGlSurfaceCreated(int width, int height) {
            super.onGlSurfaceCreated(width, height);

            renderer.create();
            renderer.onSurfaceChanged(null, width, height);
        }

        @Override
        public void onDraw() {
            super.onDraw();

            // FIXME Currently this passes a fake gl10 instance. The service needs to be investigated to determine how to get the proper gl10 and properly configure EGL
            renderer.onDrawFrame(gl10);

            // Draw every frame as long as we're visible and in interactive mode.
            if (isVisible() && !isInAmbientMode()) {
                invalidate();
            }
        }

        @Override
        public void onDestroy() {
            renderer.onSurfaceDestroyed();

            super.onDestroy();
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);

            renderer.onVisibilityChanged(visible);
            invalidate();
        }
    }

}