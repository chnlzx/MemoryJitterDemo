import pytest
from draw_heart import calculate_heart_points, map_to_rainbow_color


def test_calculate_heart_points_basic():
    """Test basic heart point calculation."""
    size = 10
    points = calculate_heart_points(size)

    # Should return 2D list
    assert isinstance(points, list)
    assert len(points) == size

    # Should have some True values (heart points)
    true_count = sum(sum(row) for row in points)
    assert true_count > 0


def test_map_to_rainbow_color_basic():
    """Test basic rainbow color mapping."""
    width = 20

    # Test left side (red)
    color_left = map_to_rainbow_color(0, 0, width)
    assert "\033[31m" in color_left  # Red ANSI code

    # Test right side (purple)
    color_right = map_to_rainbow_color(width-1, 0, width)
    assert "\033[35m" in color_right  # Purple ANSI code

    # Test intermediate color (middle of spectrum)
    color_middle = map_to_rainbow_color(width // 2, 0, width)
    assert "\033[32m" in color_middle  # Green ANSI code (middle of 6 colors)

    # Test edge case: width=1
    color_single = map_to_rainbow_color(0, 0, 1)
    assert "\033[31m" in color_single  # Should default to red
