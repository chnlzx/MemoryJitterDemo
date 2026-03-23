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


def test_main_basic():
    """Test main function runs and produces output."""
    import io
    import sys
    from contextlib import redirect_stdout
    from draw_heart import main

    # Mock sys.argv to avoid argparse picking up pytest arguments
    original_argv = sys.argv
    try:
        sys.argv = ["draw_heart.py"]  # Simulate running the script with no args

        # Redirect stdout to capture output
        f = io.StringIO()
        with redirect_stdout(f):
            main()

        output = f.getvalue()
        # After implementation, it should contain heart characters
        assert len(output) > 0
        assert "❤" in output
    finally:
        sys.argv = original_argv


def test_calculate_heart_points_boundaries():
    """Test boundary sizes."""
    # Test minimum size
    points_min = calculate_heart_points(5)
    assert len(points_min) == 5

    # Test maximum size
    points_max = calculate_heart_points(50)
    assert len(points_max) == 50


def test_calculate_heart_points_invalid_size():
    """Test invalid size handling."""
    with pytest.raises(ValueError):
        calculate_heart_points(4)

    with pytest.raises(ValueError):
        calculate_heart_points(51)


def test_map_to_rainbow_color_completeness():
    """Test all rainbow colors are generated."""
    width = 100
    colors_found = set()
    for x in range(width):
        color = map_to_rainbow_color(x, 0, width)
        colors_found.add(color)

    # Should find at least 3 different colors
    assert len(colors_found) >= 3
