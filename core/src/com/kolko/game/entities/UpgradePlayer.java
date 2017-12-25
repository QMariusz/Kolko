package com.kolko.game.entities;

public class UpgradePlayer {

	private int speedCost = 10;
	private int energyCost = 10;
	private int smallCost = 10;
	private int mediumCost = 10;
	private int bigCost = 10;
	private int bombCost = 10;
	private int turboCost = 10;
	private int powerCost = 10;
	private int birdCost = 10;
	private int teleportCost = 10;
	private int jumpCost = 10;
	private int lSpeed, lEnergy, lSmall, lMedium, lBig, lBomb, lTurbo, lPower, lBird, lTeleport, lJump;
	private Player player;
	private HUD hud;

	public UpgradePlayer(Player player, HUD hud) {
		this.player = player;
		this.hud = hud;
	}

	public void upgradeSpeed() {
		if (((player.getPoints() - this.speedCost) >= 0)&&lSpeed<18) {
			player.upgradeSpeed();
			hud.addRectangle(222+lSpeed*34, 523);
			lSpeed++;
			player.setPoints(speedCost * (-1));
			if(speedCost<200) {
				speedCost*=1.4f;
			}
			else {
				speedCost*=1.3f;
			}
		}
	}

	public void upgradeEnergy() {
		if (((player.getPoints() - this.energyCost) >= 0)&&lEnergy<18) {
			player.upgradeEnergy();
			hud.addRectangle(222+lEnergy*34, 461);
			lEnergy++;
			player.setPoints(energyCost * (-1));
			if(energyCost<200) {
				energyCost*=1.4f;
			}
			else {
				energyCost*=1.3f;
			}
		}
	}

	public void upgradeSmall() {
		if (((player.getPoints() - this.smallCost) >= 0)&&lSmall<4) {
			player.upgradeSmall();
			hud.addRectangle(268+lSmall*34, 364);
			lSmall++;
			player.setPoints(smallCost * (-1));
			smallCost*=2;
		}
	}

	public void upgradeMedium() {
		if (((player.getPoints() - this.mediumCost) >= 0)&&lMedium<4) {
			player.upgradeMedium();
			hud.addRectangle(268+lMedium*34, 316);
			lMedium++;
			player.setPoints(mediumCost * (-1));
			mediumCost*=2;
		}
	}

	public void upgradeBig() {
		if (((player.getPoints() - this.bigCost) >= 0)&&lBig<4) {
			player.upgradeBig();
			hud.addRectangle(268+lBig*34, 271);
			lBig++;
			player.setPoints(bigCost * (-1));
			bigCost*=2;
		}
	}

	public void upgradeBomb() {
		if (((player.getPoints() - this.bombCost) >= 0)&&lBomb<4) {
			player.upgradeBomb();
			hud.addRectangle(268+lBomb*34, 224);
			lBomb++;
			player.setPoints(bombCost * (-1));
			bombCost*=2;
		}
	}

	public void upgradeTurbo() {
		if (((player.getPoints() - this.turboCost) >= 0)&&lTurbo<4) {
			player.upgradeTurbo();
			hud.addRectangle(268+lTurbo*34, 176);
			lTurbo++;
			player.setPoints(turboCost * (-1));
			turboCost*=2;
		}
	}

	public void upgradePower() {
		if (((player.getPoints() - this.powerCost) >= 0)&&lPower<4) {
			player.upgradePower();
			hud.addRectangle(664+lPower*34, 363);
			lPower++;
			player.setPoints(powerCost * (-1));
			powerCost*=2;
		}
	}

	public void upgradeBird() {
		if (((player.getPoints() - this.birdCost) >= 0)&&lBird<4) {
			player.upgradeBird();
			hud.addRectangle(664+lBird*34, 320);
			lBird++;
			player.setPoints(birdCost * (-1));
			birdCost*=2;
		}
	}

	public void upgradeTeleport() {
		if (((player.getPoints() - this.teleportCost) >= 0)&&lTeleport<4) {
			player.upgradeTeleport();
			hud.addRectangle(664+lTeleport*34, 275);
			lTeleport++;
			player.setPoints(teleportCost * (-1));
			teleportCost*=2;
		}
	}

	public void upgradeJump() {
		if (((player.getPoints() - this.jumpCost) >= 0)&&lJump<4) {
			player.upgradeJump();
			hud.addRectangle(664+lJump*34, 224);
			lJump++;
			player.setPoints(jumpCost * (-1));
			jumpCost*=2;
		}
	}

	public int getSpeedCost() {
		return speedCost;
	}

	public int getEnergyCost() {
		return energyCost;
	}

	public int getSmallCost() {
		return smallCost;
	}

	public int getMediumCost() {
		return mediumCost;
	}

	public int getBigCost() {
		return bigCost;
	}

	public int getBombCost() {
		return bombCost;
	}

	public int getTurboCost() {
		return turboCost;
	}

	public int getPowerCost() {
		return powerCost;
	}

	public int getBirdCost() {
		return birdCost;
	}

	public int getTeleportCost() {
		return teleportCost;
	}

	public int getJumpCost() {
		return jumpCost;
	}

	public int getlSpeed() {
		return lSpeed;
	}

	public int getlEnergy() {
		return lEnergy;
	}

	public int getlSmall() {
		return lSmall;
	}

	public int getlMedium() {
		return lMedium;
	}

	public int getlBig() {
		return lBig;
	}

	public int getlBomb() {
		return lBomb;
	}

	public int getlTurbo() {
		return lTurbo;
	}

	public int getlPower() {
		return lPower;
	}

	public int getlBird() {
		return lBird;
	}

	public int getlTeleport() {
		return lTeleport;
	}

	public int getlJump() {
		return lJump;
	}
	
	

}
