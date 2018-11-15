package com.jagex;
/* ProjectileNode - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class ProjectileNode extends Queuable
{
	public Projectile projectile;
	static RSString aClass16_3695 = RSString.createString("Untersuchen");
	static RSString aClass16_3697;
	static boolean aBoolean3698 = true;
	public static int anInt3703;

	public static void method615(int i) {
		CacheConstants.npcCacheIdx = null;
		aClass16_3695 = null;
		aClass16_3697 = null;
		GameClient.localPlayerPointers = null;
		if (i != 11) {
			method615(-45);
		}
	}

	ProjectileNode(Projectile projectile) {
		this.projectile = projectile;
	}
	
	static {
		aClass16_3697 = RSString.createString("Lade Titelbild )2 ");
		anInt3703 = 0;
	}
}
