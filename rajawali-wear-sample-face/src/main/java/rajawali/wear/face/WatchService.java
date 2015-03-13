package rajawali.wear.face;

import android.content.Context;
import android.support.wearable.watchface.Gles2WatchFaceService;
import android.support.wearable.watchface.WatchFaceStyle;

import javax.microedition.khronos.opengles.GL10;

import rajawali.Object3D;
import rajawali.lights.DirectionalLight;
import rajawali.materials.Material;
import rajawali.materials.textures.ATexture;
import rajawali.materials.textures.Texture;
import rajawali.primitives.Sphere;
import rajawali.wear.RajawaliWatchFaceService;
import rajawali.wear.RajawaliWatchRenderer;

public class WatchService extends RajawaliWatchFaceService {

    @Override
    protected Gles2WatchFaceService.Engine getRajawaliWatchEngine() {
        return new Engine();
    }

    private final class Engine extends RajawaliWatchEngine {

        @Override
        protected WatchFaceStyle getWatchFaceStyle() {
            return new WatchFaceStyle.Builder(WatchService.this)
                    .setCardPeekMode(WatchFaceStyle.PEEK_MODE_SHORT)
                    .setBackgroundVisibility(WatchFaceStyle.BACKGROUND_VISIBILITY_INTERRUPTIVE)
                    .setShowSystemUiTime(false)
                    .build();
        }

        @Override
        protected RajawaliWatchRenderer getRenderer() {
            return new Renderer(WatchService.this);
        }

    }

    private final class Renderer extends RajawaliWatchRenderer {

        private DirectionalLight mLight;
        private Object3D mSphere;

        public Renderer(Context context) {
            super(context);
        }

        protected void initScene() {
            mLight = new DirectionalLight(1f, 0.2f, -1.0f); // set the direction
            mLight.setColor(1.0f, 1.0f, 1.0f);
            mLight.setPower(2);

            getCurrentScene().addLight(mLight);

            try {
                Material material = new Material();
                material.addTexture(new Texture("earthColors", R.drawable.earthtruecolor_nasa_big));
                material.setColorInfluence(0);
                mSphere = new Sphere(1, 24, 24);
                mSphere.setMaterial(material);
                getCurrentScene().addChild(mSphere);
            } catch (ATexture.TextureException e) {
                e.printStackTrace();
            }

            getCurrentCamera().setZ(6);
        }

        public void onDrawFrame(GL10 glUnused) {
            super.onDrawFrame(glUnused);
            mSphere.setRotY(mSphere.getRotY() + 1);
        }

    }

}
