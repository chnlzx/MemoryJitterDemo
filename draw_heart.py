#!/usr/bin/env python3
"""
Draw a rainbow heart in the terminal.
"""

import argparse
import sys


def calculate_heart_points(size: int) -> list[list[bool]]:
    """Calculate heart point matrix using mathematical equation.

    Uses heart equation: (x²+y²-1)³ - x²y³ ≤ 0
    Converts terminal coordinates (origin top-left) to math coordinates (origin center).
    """
    if size < 5 or size > 50:
        raise ValueError("Size must be between 5 and 50")

    points = []
    for row in range(size):
        row_points = []
        for col in range(size):
            # Convert to math coordinates (center at 0,0)
            # Use different scales for x and y to make heart wider (heart is naturally wider than tall)
            # x_scale smaller = wider heart, y_scale larger = taller heart
            x_scale = 2.5 / size  # Horizontal scale
            y_scale = 2.0 / size  # Vertical scale

            x = (col - size/2) * x_scale
            y = (size/2 - row) * y_scale

            # Heart equation: (x²+y²-1)³ - x²y³ ≤ 0
            equation = (x*x + y*y - 1)**3 - x*x * y*y * y
            row_points.append(equation <= 0)
        points.append(row_points)

    return points


def map_to_rainbow_color(x: int, y: int, width: int) -> str:
    """Map position to ANSI rainbow color code.

    Rainbow spectrum: Red -> Yellow -> Green -> Cyan -> Blue -> Purple
    Based on x position (horizontal direction).

    Note: y parameter is unused as color depends solely on x position.
    """
    # ANSI color codes (simple rainbow without orange)
    colors = [
        "\033[31m",  # Red
        "\033[33m",  # Yellow
        "\033[32m",  # Green
        "\033[36m",  # Cyan
        "\033[34m",  # Blue
        "\033[35m",  # Purple
    ]

    # Map x position to color index
    if width <= 1:
        color_index = 0
    else:
        color_index = int((x / (width - 1)) * (len(colors) - 1))
        color_index = max(0, min(color_index, len(colors) - 1))

    return colors[color_index]


def main():
    """Main function to draw heart in terminal."""
    parser = argparse.ArgumentParser(description="Draw a rainbow heart in the terminal")
    parser.add_argument("--size", type=int, default=20, help="Size of heart (5-50)")
    args = parser.parse_args()

    # Validate size
    if args.size < 5 or args.size > 50:
        print("Error: Size must be between 5 and 50", file=sys.stderr)
        sys.exit(1)

    # Check Python version
    if sys.version_info < (3, 6):
        print("Error: Python 3.6+ required", file=sys.stderr)
        sys.exit(1)

    # Calculate heart points
    points = calculate_heart_points(args.size)

    # Draw heart with rainbow colors
    for row in points:
        line = ""
        for col_idx, is_point in enumerate(row):
            if is_point:
                color = map_to_rainbow_color(col_idx, 0, len(row))
                line += f"{color}❤\033[0m"  # Heart character with color reset
            else:
                line += " "
        print(line)


if __name__ == "__main__":
    main()
