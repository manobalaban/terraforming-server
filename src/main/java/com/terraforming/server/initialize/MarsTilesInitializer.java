package com.terraforming.server.initialize;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.terraforming.server.constans.Resource;
import com.terraforming.server.constans.TileType;
import com.terraforming.server.model.Tile;

public class MarsTilesInitializer {

	public static Map<String, Tile> initTiles() {
		Map<String, Tile> result = new HashMap<>();
		Map<String, Tile> tiles = new HashMap<>();
		tiles.put("A8", new Tile("A8", TileType.FOR_OCEAN));
		tiles.put("A10",new Tile("A10", TileType.FOR_OCEAN, Arrays.asList(Resource.CARD, Resource.TITAN)));
		tiles.put("A12",new Tile("A12", TileType.FOR_OCEAN));
		tiles.put("A14",new Tile("A14", TileType.EMPTY, Arrays.asList(Resource.STEEL)));
		tiles.put("A16",new Tile("A16", TileType.POLAR, Arrays.asList(Resource.STEEL)));
		tiles.put("A18",new Tile("A18", TileType.POLAR, Arrays.asList(Resource.HEAT, Resource.HEAT)));
		tiles.put("A20",new Tile("A20", TileType.POLAR, Arrays.asList(Resource.HEAT, Resource.CARD)));
		tiles.put("A22",new Tile("A22", TileType.POLAR, Arrays.asList(Resource.HEAT, Resource.HEAT)));
		tiles.put("A24",new Tile("A24", TileType.EMPTY, Arrays.asList(Resource.STEEL)));
		tiles.put("A26",new Tile("A26", TileType.FOR_OCEAN));
		tiles.put("A28",new Tile("A28", TileType.FOR_OCEAN));
		tiles.put("A30",new Tile("A30", TileType.FOR_OCEAN));
		tiles.put("B5",new Tile("B5", TileType.FOR_OCEAN));
		tiles.put("B7",new Tile("B7", TileType.FOR_OCEAN, Arrays.asList(Resource.PLANT)));
		tiles.put("B9",new Tile("B9", TileType.EMPTY, Arrays.asList(Resource.PLANT)));
		tiles.put("B11",new Tile("B11", TileType.EMPTY, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("B13",new Tile("B13", TileType.FOR_OCEAN, Arrays.asList(Resource.PLANT)));
		tiles.put("B15",new Tile("B15", TileType.FOR_OCEAN, Arrays.asList(Resource.PLANT)));
		tiles.put("B17",new Tile("B17", TileType.POLAR));
		tiles.put("B19",new Tile("B19", TileType.POLAR));
		tiles.put("B21",new Tile("B21", TileType.POLAR));
		tiles.put("B23",new Tile("B23", TileType.FOR_OCEAN));
		tiles.put("B25",new Tile("B25", TileType.FOR_OCEAN));
		tiles.put("B27",new Tile("B27", TileType.FOR_OCEAN));
		tiles.put("B29",new Tile("B29", TileType.FOR_OCEAN, Arrays.asList(Resource.STEEL)));
		tiles.put("B31",new Tile("B31", TileType.FOR_OCEAN, Arrays.asList(Resource.TITAN, Resource.TITAN)));
		tiles.put("B33",new Tile("B33", TileType.FOR_OCEAN));
		tiles.put("C4",new Tile("C4", TileType.FOR_OCEAN));
		tiles.put("C6",new Tile("C6", TileType.EMPTY, Arrays.asList(Resource.PLANT)));
		tiles.put("C8",new Tile("C8", TileType.EMPTY, Arrays.asList(Resource.STEEL, Resource.STEEL)));
		tiles.put("C10",new Tile("C10", TileType.EMPTY, Arrays.asList(Resource.PLANT)));
		tiles.put("C12",new Tile("C12", TileType.EMPTY, Arrays.asList(Resource.PLANT)));
		tiles.put("C14",new Tile("C14", TileType.EMPTY, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("C16",new Tile("C16", TileType.FOR_OCEAN, Arrays.asList(Resource.CARD)));
		tiles.put("C18",new Tile("C18", TileType.FOR_OCEAN, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("C20",new Tile("C20", TileType.FOR_OCEAN, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("C22",new Tile("C22", TileType.FOR_OCEAN, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("C24",new Tile("C24", TileType.FOR_OCEAN, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("C26",new Tile("C26", TileType.FOR_OCEAN, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("C28",new Tile("C28", TileType.FOR_OCEAN, Arrays.asList(Resource.STEEL)));
		tiles.put("C30",new Tile("C30", TileType.FOR_OCEAN, Arrays.asList(Resource.CARD, Resource.CARD)));
		tiles.put("C32",new Tile("C32", TileType.FOR_OCEAN, Arrays.asList(Resource.TITAN)));
		tiles.put("C34",new Tile("C34", TileType.FOR_OCEAN));
		tiles.put("D3",new Tile("D3", TileType.FOR_OCEAN, Arrays.asList(Resource.PLANT)));
		tiles.put("D5",new Tile("D5", TileType.VULCANIC, Arrays.asList(Resource.PLANT)));
		tiles.put("D7",new Tile("D7", TileType.EMPTY, Arrays.asList(Resource.STEEL)));
		tiles.put("D9",new Tile("D9", TileType.VULCANIC, Arrays.asList(Resource.TITAN)));
		tiles.put("D11",new Tile("D11", TileType.EMPTY));
		tiles.put("D13",new Tile("D13", TileType.EMPTY, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("D15",new Tile("D15", TileType.FOR_OCEAN, Arrays.asList(Resource.CARD, Resource.CARD)));
		tiles.put("D17",new Tile("D17", TileType.FOR_OCEAN, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("D19",new Tile("D19", TileType.EMPTY, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("D21",new Tile("D21", TileType.EMPTY, Arrays.asList(Resource.PLANT)));
		tiles.put("D23",new Tile("D23", TileType.EMPTY, Arrays.asList(Resource.PLANT)));
		tiles.put("D25",new Tile("D25", TileType.EMPTY, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("D27",new Tile("D27", TileType.FOR_OCEAN, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("D29",new Tile("D29", TileType.FOR_OCEAN, Arrays.asList(Resource.STEEL)));
		tiles.put("D31",new Tile("D31", TileType.FOR_OCEAN, Arrays.asList(Resource.STEEL)));
		tiles.put("D33",new Tile("D33", TileType.VULCANIC, Arrays.asList(Resource.PLANT)));
		tiles.put("D35",new Tile("D35", TileType.VULCANIC, Arrays.asList(Resource.TITAN, Resource.TITAN)));
		tiles.put("E2",new Tile("E2", TileType.FOR_OCEAN, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("E4",new Tile("E4", TileType.VULCANIC, Arrays.asList(Resource.CARD, Resource.CARD, Resource.CARD)));
		tiles.put("E6",new Tile("E6", TileType.VULCANIC, Arrays.asList(Resource.STEEL)));
		tiles.put("E8",new Tile("E8", TileType.VULCANIC, Arrays.asList(Resource.CARD)));
		tiles.put("E10",new Tile("E10", TileType.VULCANIC, Arrays.asList(Resource.CREDIT, Resource.CREDIT, Resource.CREDIT, Resource.CREDIT, Resource.CREDIT)));
		tiles.put("E12",new Tile("E12", TileType.EMPTY));
		tiles.put("E14",new Tile("E14", TileType.FOR_OCEAN, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("E16",new Tile("E16", TileType.FOR_OCEAN, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("E18",new Tile("E18", TileType.EMPTY, Arrays.asList(Resource.PLANT)));
		tiles.put("E20",new Tile("E20", TileType.EMPTY, Arrays.asList(Resource.CARD, Resource.CARD)));
		tiles.put("E22",new Tile("E22", TileType.EMPTY));
		tiles.put("E24",new Tile("E24", TileType.EMPTY));
		tiles.put("E26",new Tile("E26", TileType.EMPTY, Arrays.asList(Resource.PLANT)));
		tiles.put("E28",new Tile("E28", TileType.FOR_OCEAN, Arrays.asList(Resource.CARD, Resource.TITAN)));
		tiles.put("E30",new Tile("E30", TileType.FOR_OCEAN, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("E32",new Tile("E32", TileType.FOR_OCEAN, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("E34",new Tile("E34", TileType.VULCANIC, Arrays.asList(Resource.STEEL, Resource.STEEL)));
		tiles.put("E36",new Tile("E36", TileType.EMPTY, Arrays.asList(Resource.PLANT)));
		tiles.put("F1",new Tile("F1", TileType.FOR_OCEAN, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("F3",new Tile("F3", TileType.FOR_OCEAN, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("F5",new Tile("F5", TileType.VULCANIC, Arrays.asList(Resource.PLANT)));
		tiles.put("F7",new Tile("F7", TileType.VULCANIC, Arrays.asList(Resource.PLANT, Resource.TITAN)));
		tiles.put("F9",new Tile("F9", TileType.EMPTY, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("F11",new Tile("F11", TileType.EMPTY, Arrays.asList(Resource.PLANT)));
		tiles.put("F13",new Tile("F13", TileType.EMPTY, Arrays.asList(Resource.PLANT)));
		tiles.put("F15",new Tile("F15", TileType.FOR_OCEAN, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("F17",new Tile("F17", TileType.EMPTY, Arrays.asList(Resource.PLANT)));
		tiles.put("F19",new Tile("F19", TileType.EMPTY));
		tiles.put("F21",new Tile("F21", TileType.EMPTY));
		tiles.put("F23",new Tile("F23", TileType.EMPTY));
		tiles.put("F25",new Tile("F25", TileType.EMPTY));
		tiles.put("F27",new Tile("F27", TileType.EMPTY, Arrays.asList(Resource.PLANT)));
		tiles.put("F29",new Tile("F29", TileType.EMPTY, Arrays.asList(Resource.PLANT)));
		tiles.put("F31",new Tile("F31", TileType.EMPTY, Arrays.asList(Resource.PLANT)));
		tiles.put("F33",new Tile("F33", TileType.FOR_OCEAN, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("F35",new Tile("F35", TileType.FOR_OCEAN, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("F37",new Tile("F37", TileType.FOR_OCEAN, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("G2",new Tile("G2", TileType.EMPTY, Arrays.asList(Resource.PLANT)));
		tiles.put("G4",new Tile("G4", TileType.EMPTY, Arrays.asList(Resource.PLANT)));
		tiles.put("G6",new Tile("G6", TileType.VULCANIC, Arrays.asList(Resource.CARD, Resource.TITAN)));
		tiles.put("G8",new Tile("G8", TileType.NOCTIS_CITY, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("G10",new Tile("G10", TileType.EMPTY, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("G12",new Tile("G12", TileType.EMPTY, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("G14",new Tile("G14", TileType.EMPTY, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("G16",new Tile("G16", TileType.EMPTY, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("G18",new Tile("G18", TileType.EMPTY));
		tiles.put("G20",new Tile("G20", TileType.EMPTY, Arrays.asList(Resource.STEEL)));
		tiles.put("G22",new Tile("G22", TileType.EMPTY, Arrays.asList(Resource.STEEL)));
		tiles.put("G24",new Tile("G24", TileType.EMPTY));
		tiles.put("G26",new Tile("G26", TileType.EMPTY, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("G28",new Tile("G28", TileType.EMPTY, Arrays.asList(Resource.PLANT)));
		tiles.put("G30",new Tile("G30", TileType.EMPTY, Arrays.asList(Resource.PLANT)));
		tiles.put("G32",new Tile("G32", TileType.EMPTY, Arrays.asList(Resource.PLANT)));
		tiles.put("G34",new Tile("G34", TileType.EMPTY, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("G36",new Tile("G36", TileType.EMPTY, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("H3",new Tile("H3", TileType.EMPTY));
		tiles.put("H5",new Tile("H5", TileType.EMPTY));
		tiles.put("H7",new Tile("H7", TileType.EMPTY));
		tiles.put("H9",new Tile("H9", TileType.EMPTY, Arrays.asList(Resource.STEEL)));
		tiles.put("H11",new Tile("H11", TileType.EMPTY));
		tiles.put("H13",new Tile("H13", TileType.EMPTY, Arrays.asList(Resource.PLANT)));
		tiles.put("H15",new Tile("H15", TileType.EMPTY, Arrays.asList(Resource.PLANT)));
		tiles.put("H17",new Tile("H17", TileType.EMPTY));
		tiles.put("H19",new Tile("H19", TileType.EMPTY, Arrays.asList(Resource.STEEL)));
		tiles.put("H21",new Tile("H21", TileType.EMPTY, Arrays.asList(Resource.STEEL, Resource.STEEL)));
		tiles.put("H23",new Tile("H23", TileType.EMPTY, Arrays.asList(Resource.STEEL)));
		tiles.put("H25",new Tile("H25", TileType.FOR_OCEAN, Arrays.asList(Resource.PLANT)));
		tiles.put("H27",new Tile("H27", TileType.FOR_OCEAN, Arrays.asList(Resource.PLANT)));
		tiles.put("H29",new Tile("H29", TileType.EMPTY, Arrays.asList(Resource.PLANT)));
		tiles.put("H31",new Tile("H31", TileType.EMPTY));
		tiles.put("H33",new Tile("H33", TileType.EMPTY, Arrays.asList(Resource.CARD)));
		tiles.put("H35",new Tile("H35", TileType.EMPTY, Arrays.asList(Resource.CARD)));
		tiles.put("I4",new Tile("I4", TileType.EMPTY, Arrays.asList(Resource.TITAN, Resource.STEEL)));
		tiles.put("I6",new Tile("I6", TileType.EMPTY));
		tiles.put("I8",new Tile("I8", TileType.EMPTY, Arrays.asList(Resource.STEEL)));
		tiles.put("I10",new Tile("I10", TileType.EMPTY, Arrays.asList(Resource.STEEL, Resource.STEEL)));
		tiles.put("I12",new Tile("I12", TileType.EMPTY, Arrays.asList(Resource.PLANT)));
		tiles.put("I14",new Tile("I14", TileType.FOR_OCEAN, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("I16",new Tile("I16", TileType.FOR_OCEAN, Arrays.asList(Resource.CARD, Resource.CARD)));
		tiles.put("I18",new Tile("I18", TileType.EMPTY));
		tiles.put("I20",new Tile("I20", TileType.EMPTY, Arrays.asList(Resource.STEEL)));
		tiles.put("I22",new Tile("I22", TileType.EMPTY));
		tiles.put("I24",new Tile("I24", TileType.FOR_OCEAN, Arrays.asList(Resource.TITAN)));
		tiles.put("I26",new Tile("I26", TileType.FOR_OCEAN, Arrays.asList(Resource.CARD, Resource.CARD, Resource.CARD)));
		tiles.put("I28",new Tile("I28", TileType.FOR_OCEAN, Arrays.asList(Resource.PLANT, Resource.PLANT)));
		tiles.put("I30",new Tile("I30", TileType.EMPTY));
		tiles.put("I32",new Tile("I32", TileType.EMPTY, Arrays.asList(Resource.STEEL)));
		tiles.put("I34",new Tile("I34", TileType.EMPTY, Arrays.asList(Resource.STEEL, Resource.STEEL)));
		tiles.put("J5",new Tile("J5", TileType.EMPTY));
		tiles.put("J7",new Tile("J7", TileType.EMPTY));
		tiles.put("J9",new Tile("J9", TileType.EMPTY, Arrays.asList(Resource.CARD)));
		tiles.put("J11",new Tile("J11", TileType.EMPTY, Arrays.asList(Resource.CARD)));
		tiles.put("J13",new Tile("J13", TileType.EMPTY, Arrays.asList(Resource.STEEL)));
		tiles.put("J15",new Tile("J15", TileType.FOR_OCEAN, Arrays.asList(Resource.TITAN, Resource.TITAN)));
		tiles.put("J17",new Tile("J17", TileType.EMPTY, Arrays.asList(Resource.HEAT, Resource.HEAT)));
		tiles.put("J19",new Tile("J19", TileType.EMPTY, Arrays.asList(Resource.STEEL)));
		tiles.put("J21",new Tile("J21", TileType.EMPTY, Arrays.asList(Resource.HEAT, Resource.HEAT)));
		tiles.put("J23",new Tile("J23", TileType.EMPTY, Arrays.asList(Resource.PLANT)));
		tiles.put("J25",new Tile("J25", TileType.FOR_OCEAN, Arrays.asList(Resource.HEAT, Resource.HEAT)));
		tiles.put("J27",new Tile("J27", TileType.FOR_OCEAN, Arrays.asList(Resource.TITAN)));
		tiles.put("J29",new Tile("J29", TileType.EMPTY));
		tiles.put("J31",new Tile("J31", TileType.EMPTY));
		tiles.put("J33",new Tile("J33", TileType.EMPTY, Arrays.asList(Resource.STEEL)));
		tiles.put("K8",new Tile("K8", TileType.EMPTY));
		tiles.put("K10",new Tile("K10", TileType.EMPTY));
		tiles.put("K12",new Tile("K12", TileType.EMPTY));
		tiles.put("K14",new Tile("K14", TileType.EMPTY));
		tiles.put("K16",new Tile("K16", TileType.EMPTY, Arrays.asList(Resource.HEAT, Resource.HEAT)));
		tiles.put("K18",new Tile("K18", TileType.EMPTY)); //TODO OCEAN FOR CREDIT
		tiles.put("K20",new Tile("K20", TileType.EMPTY, Arrays.asList(Resource.TITAN)));
		tiles.put("K22",new Tile("K22", TileType.EMPTY, Arrays.asList(Resource.HEAT, Resource.HEAT)));
		tiles.put("K24",new Tile("K24", TileType.EMPTY));
		tiles.put("K26",new Tile("K26", TileType.EMPTY));
		tiles.put("K28",new Tile("K28", TileType.EMPTY, Arrays.asList(Resource.TITAN)));
		tiles.put("K30",new Tile("K230", TileType.EMPTY));
		
		for(Map.Entry<String, Tile> tile : tiles.entrySet()) {
			int row = getRowCode(tile.getKey().substring(0, 1));
			int column = Integer.parseInt(tile.getKey().substring(1));
			for(Map.Entry<String, Tile> adjecentTile : tiles.entrySet()) {
				int x = getRowCode(adjecentTile.getKey().substring(0, 1));
				int y = Integer.parseInt(adjecentTile.getKey().substring(1));
				if((row == x && (column - 2 == y || column + 2 == y)) || (row - 1 == x && (column - 1 == y || column + 1 == y)) || (row + 1 == x && (column - 1 == y || column + 1 == y))) {
					tile.getValue().addAdjecent(adjecentTile.getValue().getName());
				}
			}
			result.put(tile.getKey(), tile.getValue());
		}
		return result;
	}
	
	private static int getRowCode(String s) {
		int result;
		switch (s) {
		case "A":
			result = 1;
			break;
		case "B":
			result = 2;
			break;
		case "C":
			result = 3;
			break;
		case "D":
			result = 4;
			break;
		case "E":
			result = 5;
			break;
		case "F":
			result = 6;
			break;
		case "G":
			result = 7;
			break;
		case "H":
			result = 8;
			break;
		case "I":
			result = 9;
			break;
		case "J":
			result = 10;
			break;
		case "K":
			result = 11;
			break;
		default:
			result = 0;
		}
		return result;
	}
}
