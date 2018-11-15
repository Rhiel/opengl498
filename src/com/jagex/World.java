package com.jagex;
/**
 * A world.
 * @author Apache Ah64
 */
public class World {

	/**
	 * The world id.
	 */
	public int id;
	
	/**
	 * The free/member flag.
	 */
	public boolean flag;
	
	/**
	 * The world adress.
	 */
	public String adress;
	
	/**
	 * The players online count.
	 */
	public int players;
	
	/**
	 * The world's country flag.
	 */
	public int countryFlag;
	
	/**
	 * The world contructor.
	 * @param id
	 * @param flag
	 * @param adress
	 * @param players
	 * @param countryFlag
	 */
	public World(int id, boolean flag, String adress, int players, int countryFlag) {
		this.id = id;
		this.flag = flag;
		this.adress = adress;
		this.players = players;
		this.countryFlag = countryFlag;
	}

	/**
	 * Get the world id.
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the world id.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Is free/member flag.
	 * @return
	 */
	public boolean isFlag() {
		return flag;
	}

	/**
	 * Set free/member flag.
	 * @param flag
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	/**
	 * Get the world's adress.
	 * @return
	 */
	public String getAdress() {
		return adress;
	}

	/**
	 * Get the world's player count.
	 * @return
	 */
	public int getPlayers() {
		return players;
	}

	/**
	 * Set the world's adress.
	 * @param adress
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}

	/**
	 * Set the world's player count.
	 * @param players
	 */
	public void setPlayers(int players) {
		this.players = players;
	}

	/**
	 * Get the world's country flag.
	 * @return
	 */
	public int getCountryFlag() {
		return countryFlag;
	}

	/**
	 * Set the world's country flag.
	 * @param countryFlag
	 */
	public void setCountryFlag(int countryFlag) {
		this.countryFlag = countryFlag;
	}
	
}
