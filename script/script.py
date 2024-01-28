from flask import Flask, render_template
import pandas as pd
import os, json

# Obtenez le chemin absolu du répertoire actuel du script
script_directory = os.path.dirname(os.path.abspath(__file__))
# Construisez le chemin relatif vers le fichier CSV
film_affiche_csv = os.path.join(script_directory, '../talend/_output/film_affiche.csv')

app = Flask(__name__)

# Charger le fichier CSV provenant de Talend
df_films = pd.read_csv(film_affiche_csv, sep=';')
# Convertir la colonne 'genres' en liste
df_films['genres'] = df_films['genres'].apply(json.loads)

@app.route('/')
def index():
    return render_template("index.html", films=df_films.to_dict(orient='records'))

if __name__ == '__main__':
    app.run(debug=True)