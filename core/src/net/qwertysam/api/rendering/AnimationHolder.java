package net.qwertysam.api.rendering;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationHolder
{
	/** The default frame speed. */
	public static final float DEFAULT_FRAME_SPEED = 0.2F;
	/** The animation object. */
	private Animation animation;
	/** The total time elapsed of the animation. */
	private float stateTime = 0F;
	
	public AnimationHolder(List<Sprite> sprites)
	{
		this(sprites, DEFAULT_FRAME_SPEED);
	}
	
	public AnimationHolder(List<Sprite> sprites, float frameSpeed)
	{
		animation = new Animation(frameSpeed, sprites.toArray(new Sprite[sprites.size()]));
		
		// animation = new Animation(frameSpeed, sprites);
	}
	
	/**
	 * Gets the Animation instance.
	 * 
	 * @return the Animation instance.
	 */
	public Animation getAnimation()
	{
		return animation;
	}
	
	/**
	 * Gets the printable texture for the frame in the animation after the specified time has passed
	 * 
	 * @param interval the interval since the last time this animation was updated
	 * @return the frame of the animation at its state time.
	 */
	public TextureRegion getKeyFrame(float delta)
	{
		stateTime += delta;
		
		return (TextureRegion) animation.getKeyFrame(stateTime, true);
	}
}
