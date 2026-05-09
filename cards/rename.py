import os

# Definition der Übersetzung
farben = {
    'C': 'Kreuz',
    'D': 'Karo',
    'H': 'Herz',
    'S': 'Piek'
}

werte = {
    'A': 'Ass',
    'J': 'Bube',
    'Q': 'Dame',
    'K': 'König'
}

def rename_cards():
    # Alle Dateien im aktuellen Verzeichnis durchgehen
    for filename in os.listdir('.'):
        if filename.endswith('.png') and '-' in filename:
            # Beispiel: "10-C.png" -> part1="10", part2="C.png"
            name_part, ext_part = filename.split('-')
            suit_letter = ext_part.replace('.png', '') # "C"
            
            # Wert bestimmen (Zahl oder Buchstabe übersetzen)
            kartenzahl = werte.get(name_part, name_part)
            
            # Farbe bestimmen
            farbname = farben.get(suit_letter)
            
            if farbname:
                new_name = f"{farbname}-{kartenzahl}.png"
                print(f"Benenne um: {filename} -> {new_name}")
                os.rename(filename, new_name)

if __name__ == "__main__":
    rename_cards()
