package picUtility;

public class Cricket {
	int[] possibleRunsValue = { 0, 1, 2, 3, 4, 6, 0, 1, 2, 3, 1, 0 };
	String[] possibleBallValue = { "Valid Ball", "Wide Ball", "No Ball", "No Ball", "Valid Ball", "Valid Ball",
			"Valid Ball", "Wide Ball", "Valid Ball", "Valid Ball", "Wide Ball", "Valid Ball" };
	String[] possibleOuts = { "Not Out", "Bowled", "Not Out", "LBW", "Not Out", "Stumping", "Not Out", "Catch",
			"Run Out", "Not Out", "Hit Wicket", "Not Out" };

	float i = (float) 0.0;
	float totalOvers = (float) 0.0;
	private int target = 0;

	final int MAX_OVERS = 1;
	private int over;
	private int ball;
	private int totalRuns;
	private int totalExtraRuns;
	private int teamTotalRuns;
	private int wickets;
	private int noBalls;
	private int wides;
	private int currentRun;
	private int currentExtraRun;
	private String currentBall;
	private String currentOut;
	private String batsmanStatus;
	private float teamRunRate;

	public Cricket() {
		super();
		this.i = 0;
		this.totalOvers = 0;
		this.over = 0;
		this.ball = 0;
		this.totalRuns = 0;
		this.totalExtraRuns = 0;
		this.teamTotalRuns = 0;
		this.wickets = 0;
		this.noBalls = 0;
		this.wides = 0;
		this.currentRun = 0;
		this.currentExtraRun = 0;
		this.currentBall = null;
		this.currentOut = null;
		this.batsmanStatus = null;
		this.teamRunRate = (float) 0.0;
	}

	public int generateRandomNumber() {
		int minimum = 1;
		int maximum = 12;
		return (minimum + (int) (Math.random() * maximum));
	}

	public int generateRun() {
		return possibleRunsValue[generateRandomNumber() - 1];
	}

	public String generateBall() {
		return possibleBallValue[generateRandomNumber() - 1];
	}

	public String generateOuts() {
		return possibleOuts[generateRandomNumber() - 1];
	}

	public void printEndLine() {
		System.out.println("*********************************************************************");
	}

	public void printOver() {
		this.printEndLine();
		System.out.println("                                 Over");
		this.printEndLine();
	}

	public void updateOut() {
		setCurrentRun(0);
		setCurrentExtraRun(0);
		setWickets(getWickets() + 1);
	}

	public void updateExtra() {
		setCurrentExtraRun(1);
		setCurrentRun(generateRun());
		setCurrentOut("Not Out");
	}

	public void checkIfOut() {
		currentOut = generateOuts();
		setCurrentOut(currentOut);
		switch (currentOut) {
		case "Not Out":
			setCurrentRun(generateRun());
			setCurrentExtraRun(0);
			break;
		case "Bowled":
			updateOut();
			break;
		case "Stumping":
			updateOut();
			break;
		case "LBW":
			updateOut();
			break;
		case "Catch":
			updateOut();
			break;
		case "Hit Wicket":
			updateOut();
			break;
		case "Run Out":
			updateOut();
			break;
		}
	}

	public void displayScore() {
		System.out.println("Over: " + over + "." + (ball % 6));
		System.out.println(" ->" + "Current Ball :" + currentBall);
		System.out.println(" ->" + "Current Out :" + getCurrentOut());
		System.out.println(" ->" + "Current Runs :" + getCurrentRun());
		System.out.println(" ->" + "Current Extra Run :" + getCurrentExtraRun());
		// System.out.println(" ->" + "Total Extra Runs (" + " Wides :" +
		// getWides() + " No Balls :" + getNoBalls()
		// + " ) " + getTotalExtraRuns());
		System.out.println(" ->" + "Team Total Runs :" + getTeamTotalRuns() + "/" + getWickets() + " Extras (w "
				+ getWides() + ", nb " + getNoBalls() + ") " + getTotalExtraRuns());
		System.out.println(" ->Run Rate :" + getTeamRunRate());
	}

	public void playMe3() {
		while (over < MAX_OVERS && wickets < 10) {
			currentBall = generateBall();
			switch (currentBall) {
			case "Valid Ball":
				checkIfOut();
				if (getWickets() < 10) {
					ball = ball + 1;
				}
				if (ball % 6 == 0) {
					over++;
				}
				break;
			case "Wide Ball":
				updateExtra();
				setWides(getWides() + 1);
				break;
			case "No Ball":
				updateExtra();
				setNoBalls(getNoBalls() + 1);
				break;
			}

			setTotalRuns(getTotalRuns() + getCurrentRun());
			setTotalExtraRuns(getTotalExtraRuns() + getCurrentExtraRun());
			setTeamTotalRuns(getTotalRuns() + getTotalExtraRuns());

			// setTarget(getTeamTotalRuns());

			// if ((float) (over + ball) == 0.0) {
			// if (getTeamTotalRuns() == 0) {
			// setTeamRunRate((float) 0.0);
			// } else
			// setTeamRunRate((float) getTeamTotalRuns());
			//
			// } else if ((float) (over + ball) < (float) 0.5) {
			// setTeamRunRate((float) getTeamTotalRuns() / (ball % 6));
			// } else if ((float) (over + ball) >= (float) 1.0) {
			// setTeamRunRate((float) getTeamTotalRuns() / ((float) (over)));
			// }

			// System.out.println(over + "." + (ball % 6) + "\t" + currentBall +
			// "\t\t" + currentRun + "\t\t" + currentExtraRun
			// + "\t" + totalExtraRuns);
			displayScore();
			setTarget(getTeamTotalRuns());
			// if (over == MAX_OVERS && wickets == 10 && getTarget() == 0) {
			// setTarget(getTeamTotalRuns());
			// }
			int newTarget=getTarget();
			
			if (getTarget() > newTarget) {
				System.out.println("Team 2 won");
			} else
				System.out.println("Team 1 won");
		}

	}
	// if (getTeamTotalRuns() >= getTarget()) {
	// System.out.println("Team 2 won");
	// } else
	// System.out.println("Team 1 won");

	public void playMe2() {
		// System.out.println("Overs" + "\t" + "Current Ball" + "\t" + "Current
		// Runs" + "\t" + "Current Extra Run" + "\t"
		// + "Total Extra Run");
		while (over < MAX_OVERS && wickets < 10) {
			// if(over<=MAX_OVERS || wickets<=10){
			// setTarget(getTeamTotalRuns());
			// }

			currentBall = generateBall();
			switch (currentBall) {
			case "Valid Ball":
				currentOut = generateOuts();
				setCurrentOut(currentOut);
				switch (currentOut) {
				case "Not Out":
					setCurrentRun(generateRun());
					setCurrentExtraRun(0);
					break;
				case "Bowled":
					updateOut();
					break;
				case "Stumping":
					updateOut();
					break;
				case "LBW":
					updateOut();
					break;
				case "Catch":
					updateOut();
					break;
				case "Hit Wicket":
					updateOut();
					break;
				case "Run Out":
					updateOut();
					break;
				}
				if (getWickets() < 10) {
					ball = ball + 1;
				}

				if (ball % 6 == 0) {
					over++;
				}
				break;
			case "Wide Ball":
				updateExtra();
				setWides(getWides() + 1);
				break;
			case "No Ball":
				updateExtra();
				setNoBalls(getNoBalls() + 1);
				break;
			}

			setTotalRuns(getTotalRuns() + getCurrentRun());
			setTotalExtraRuns(getTotalExtraRuns() + getCurrentExtraRun());
			setTeamTotalRuns(getTotalRuns() + getTotalExtraRuns());

			setTarget(getTeamTotalRuns());

			if ((float) (over + ball) == 0.0) {
				if (getTeamTotalRuns() == 0) {
					setTeamRunRate((float) 0.0);
				} else
					setTeamRunRate((float) getTeamTotalRuns());

			} else if ((float) (over + ball) < (float) 0.5) {
				setTeamRunRate((float) getTeamTotalRuns() / (ball % 6));
			} else if ((float) (over + ball) >= (float) 1.0) {
				setTeamRunRate((float) getTeamTotalRuns() / ((float) (over)));
			}

			// System.out.println(over + "." + (ball % 6) + "\t" + currentBall +
			// "\t\t" + currentRun + "\t\t" + currentExtraRun
			// + "\t" + totalExtraRuns);

			System.out.println("Over: " + over + "." + (ball % 6));
			System.out.println(" ->" + "Current Ball :" + currentBall);
			System.out.println(" ->" + "Current Out :" + getCurrentOut());
			System.out.println(" ->" + "Current Runs :" + getCurrentRun());
			System.out.println(" ->" + "Current Extra Run :" + getCurrentExtraRun());
			// System.out.println(" ->" + "Total Extra Runs (" + " Wides :" +
			// getWides() + " No Balls :" + getNoBalls()
			// + " ) " + getTotalExtraRuns());
			System.out.println(" ->" + "Team Total Runs :" + getTeamTotalRuns() + "/" + getWickets() + " Extras (w "
					+ getWides() + ", nb " + getNoBalls() + ") " + getTotalExtraRuns());
			System.out.println(" ->Run Rate :" + getTeamRunRate());
		}
		System.out.println("Target :" + getTarget());
	}

	public void playMe() {
		float maxOvers = (float) 2.0;
		int ball = 0;
		float overs = (float) 0.0;
		while (overs < maxOvers) {
			currentBall = generateBall();
			switch (currentBall) {
			case "Valid Ball":
				currentRun = generateRun();
				currentExtraRun = 0;
				ball = ball + 1;
				break;
			}
			overs = (float) ball / 10;
			if (ball == 6) {
				ball = 0;
				if (overs < 1.0) {
					overs = (float) 1.0;
				} else
					overs = (float) (overs + 1.0);
			}
			System.out.println("Over :" + overs);

		}

	}

	public void playCricket() {
		while (totalOvers < (float) MAX_OVERS) {
			while (i < (float) 0.6) {
				// for (i = (float) 0.1; i <= (float) 0.6; i += (float) 0.1) {
				currentBall = generateBall();
				switch (currentBall) {
				case "Valid Ball":
					currentRun = generateRun();
					currentExtraRun = 0;
					// totalRuns = totalRuns + currentRun;
					i += (float) 0.1;
					over += (float) 0.1;
					break;
				case "Wide":
					// currentRun = cric.generateRun();
					currentRun = 0;
					int wideRun = generateRun();
					i += (float) 0.0;
					currentExtraRun = 1;
					if (wideRun != 6) {
						currentExtraRun = currentExtraRun + wideRun;
					}
					wides = wides + currentExtraRun;
					// totalRuns = totalRuns + currentRun;
					break;
				case "No Ball":
					noBalls = noBalls + 1;
					currentRun = generateRun();
					i += (float) 0.0;
					// currentExtraRun=1;
					currentExtraRun = currentExtraRun + 1;
					// totalRuns = totalRuns + currentRun;
					break;
				case "Dead Ball":
					currentRun = 0;
					currentExtraRun = 0;
					i += (float) 0.0;
					// totalRuns = totalRuns + currentRun;
					break;
				}

				totalExtraRuns = totalExtraRuns + currentExtraRun;
				totalRuns = totalRuns + currentRun + currentExtraRun;

				System.out.println("Ball :" + (float) i);
				System.out.println("|Current Ball: " + currentBall + " |Current Run :" + currentRun
						+ " |Current Extra Run :" + currentExtraRun);
				System.out.println("|Total Overs :" + (float) over + " |Total Extra Runs : " + totalExtraRuns
						+ "|Total Wides :" + wides + "|Total No Balls :" + noBalls + " |Total Runs :" + totalRuns);
				this.printEndLine();
				if (i == (float) 0.6) {
					this.printOver();
					// over = (float) 1.0;

					// System.out.println((float) overs);
				}
			}
			totalOvers = (float) (totalOvers + over);
		}
	}

	public float getI() {
		return i;
	}

	public void setI(float i) {
		this.i = i;
	}

	public float getTotalOvers() {
		return totalOvers;
	}

	public void setTotalOvers(float totalOvers) {
		this.totalOvers = totalOvers;
	}

	public int getOver() {
		return over;
	}

	public void setOver(int over) {
		this.over = over;
	}

	public int getBall() {
		return ball;
	}

	public void setBall(int ball) {
		this.ball = ball;
	}

	public int getTotalRuns() {
		return totalRuns;
	}

	public void setTotalRuns(int totalRuns) {
		this.totalRuns = totalRuns;
	}

	public int getTotalExtraRuns() {
		return totalExtraRuns;
	}

	public void setTotalExtraRuns(int totalExtraRuns) {
		this.totalExtraRuns = totalExtraRuns;
	}

	public int getTeamTotalRuns() {
		return teamTotalRuns;
	}

	public void setTeamTotalRuns(int teamTotalRuns) {
		this.teamTotalRuns = teamTotalRuns;
	}

	public int getWickets() {
		return wickets;
	}

	public void setWickets(int wickets) {
		this.wickets = wickets;
	}

	public int getNoBalls() {
		return noBalls;
	}

	public void setNoBalls(int noBalls) {
		this.noBalls = noBalls;
	}

	public int getWides() {
		return wides;
	}

	public void setWides(int wides) {
		this.wides = wides;
	}

	public int getCurrentRun() {
		return currentRun;
	}

	public void setCurrentRun(int currentRun) {
		this.currentRun = currentRun;
	}

	public int getCurrentExtraRun() {
		return currentExtraRun;
	}

	public void setCurrentExtraRun(int currentExtraRun) {
		this.currentExtraRun = currentExtraRun;
	}

	public String getCurrentBall() {
		return currentBall;
	}

	public void setCurrentBall(String currentBall) {
		this.currentBall = currentBall;
	}

	public String getCurrentOut() {
		return currentOut;
	}

	public void setCurrentOut(String currentOut) {
		this.currentOut = currentOut;
	}

	public String getBatsmanStatus() {
		return batsmanStatus;
	}

	public void setBatsmanStatus(String batsmanStatus) {
		this.batsmanStatus = batsmanStatus;
	}

	public float getTeamRunRate() {
		return teamRunRate;
	}

	public void setTeamRunRate(float teamRunRate) {
		this.teamRunRate = teamRunRate;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

}