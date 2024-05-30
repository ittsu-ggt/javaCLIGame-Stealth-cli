def replace_text(input_file, output_file, replacement_table):
    with open(input_file, 'r') as file:
        text = file.read()

    for old, new in replacement_table.items():
        text = text.replace(old, new)

    with open(output_file, 'w') as file:
        file.write(text)

# Example usage
input_file = './popup.txt'
fgcolor_file = './popupfgcolor.txt'
bgcolor_file = './popupbgcolor.txt'
fg_replacement_table = {
    '　': '8',
    'Ｒ': '8',
    '＠': '8',
    '＃': '6',
    # Add more replacement rules as needed
}
bg_replacement_table = {
    '　': '8',
    'Ｒ': '5',
    '＠': '8',
    '＃': '6',
    # Add more replacement rules as needed
}

replace_text(input_file, fgcolor_file, fg_replacement_table)
replace_text(input_file, bgcolor_file, bg_replacement_table)