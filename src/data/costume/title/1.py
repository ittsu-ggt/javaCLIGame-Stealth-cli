# Read the original file
with open('original_file.txt', 'r') as file:
    lines = file.readlines()

# Double the width and height
doubled_lines = []
for line in lines:
    doubled_line = ''
    for char in line:
        doubled_line += char * 2
    doubled_lines.append(doubled_line)

# Double the spacing between characters
final_lines = []
for line in doubled_lines:
    final_line = ''
    for char in line:
        final_line += char + ' '
    final_lines.append(final_line)

# Double the height
final_content = []
for line in final_lines:
    final_content.append(line)
    final_content.append(line)

# Write the modified content to a new file
with open('modified_file.txt', 'w') as file:
    file.writelines(final_content)