package net.qwertysam.api.gui.screen;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.qwertysam.api.gui.GuiButton;
import net.qwertysam.api.rendering.RenderableHolder;
import net.qwertysam.api.util.IDisposable;
import net.qwertysam.main.MyGdxGame;

public abstract class GuiScreen extends RenderableHolder<GuiButton> implements Screen, IGuiScreen, IDisposable
{
	public static final int MAX_TOUCHES = 4; // Typically hardware limit is 10
	
	private boolean isTouched;
	protected List<Vector2> touches;
	
	protected MyGdxGame game;
	protected OrthographicCamera camera;
	private Viewport viewport;
	protected SpriteBatch batch;
	
	/** The x offset of the buttons. */
	private float xButtonOffset;
	/** The y offset of the buttons. */
	private float yButtonOffset;
	
	public GuiScreen(MyGdxGame game)
	{
		super();
		
		isTouched = false;
		touches = new ArrayList<Vector2>();
		
		this.game = game;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(game.isInverted(), 720, 1280);
		
		viewport = new FitViewport(720, 1280, camera);
		
		batch = new SpriteBatch();
		
		init();
	}
	
	public void init()
	{}
	
	@Override
	public void show()
	{
	
	}
	
	public void update(float delta)
	{
	
	}
	
	@Override
	public void render(float delta)
	{
		tick(delta);
		
		// Resizes sprite batch to the screen size
		batch.setProjectionMatrix(camera.combined);
		
		// Clears the screen
		Gdx.gl.glClearColor(1F, 1F, 1F, 1F);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Begins the drawing phase
		batch.begin();
		
		// Draws the actual shiz in the screen
		drawScreen(delta);
		
		// Draws the buttons over everything
		renderEntries(batch);
		
		// Ends the drawing phase
		batch.end();
		
		// Updates the camera
		camera.update();
	}
	
	@Override
	public void tick(float delta)
	{
		touches.clear();
		
		isTouched = Gdx.input.isTouched();
		
		for (int i = 0; i < MAX_TOUCHES; i++)
		{
			if (Gdx.input.isTouched(i))
			{
				Vector3 vector3 = viewport.unproject(new Vector3(Gdx.input.getX(i), Gdx.input.getY(i), 0F));
				touches.add(new Vector2(vector3.x, vector3.y));
			}
		}
		
		if (!isEmpty()) buttonTick(touches, camera.position.x - (camera.viewportWidth / 2), camera.position.y - (camera.viewportHeight / 2));
	}
	
	@Override
	public void drawScreen(float delta)
	{
	
	}
	
	@Override
	public void resize(int width, int height)
	{
		System.out.println("Resizing ViewPort: (" + width + ", " + height + ")");
		viewport.update(width, height, true);
	}
	
	@Override
	public void pause()
	{
	
	}
	
	@Override
	public void resume()
	{
	
	}
	
	@Override
	public void hide()
	{
	
	}
	
	@Override
	public void dispose()
	{
		batch.dispose();
	}
	
	public float getWidth()
	{
		return camera.viewportWidth;
	}
	
	public float getHeight()
	{
		return camera.viewportHeight;
	}
	
	public OrthographicCamera getCamera()
	{
		return camera;
	}
	
	public SpriteBatch getBatch()
	{
		return batch;
	}
	
	public MyGdxGame getGame()
	{
		return game;
	}
	
	/**
	 * Ticks all buttons in this.
	 * 
	 * @param touch the touch position on the screen
	 */
	public void buttonTick(List<Vector2> touch)
	{
		buttonTick(touch, 0F, 0F);
	}
	
	/**
	 * Ticks all buttons in this.
	 * 
	 * @param touches the touches' positions on the screen
	 * @param xOffset the bottom left x ordinate of the camera position relative to the world
	 * @param yOffset the bottom left y ordinate of the camera position relative to the world
	 */
	public void buttonTick(List<Vector2> touches, float xOffset, float yOffset)
	{
		xButtonOffset = xOffset;
		yButtonOffset = yOffset;
		
		if (touches.isEmpty())
		{
			updateButtons(null);
		}
		else
		{
			for (Vector2 touch : touches)
			{
				updateButtons(touch.add(xOffset, yOffset));// new Vector2(touch.x - xOffset, touch.y - yOffset)
			}
		}
	}
	
	@Override
	public void renderEntries(SpriteBatch batch)
	{
		renderEntries(batch, xButtonOffset, yButtonOffset);
	}
	
	private void updateButtons(Vector2 touch)
	{
		for (GuiButton entry : getEntries())
		{
			entry.update(touch);
		}
	}
	
	public abstract void pressAction(int buttonID);
	
	public abstract void releaseAction(int buttonID);
	
	public boolean isTouched()
	{
		return isTouched;
	}
	
	public List<Vector2> getTouches()
	{
		return touches;
	}
}
