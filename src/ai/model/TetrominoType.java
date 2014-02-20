package ai.model;

public enum TetrominoType
{
	O, I, J, L, S, Z, T;

	public static TetrominoType getRandomTetrominoType()
	{
		int r = (int) (Math.random() * values().length);
		return values()[r];
	}
}
