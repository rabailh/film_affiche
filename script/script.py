from flask import Flask, render_template
import pandas as pd
import os, json
import re
import webbrowser

# Obtenez le chemin absolu du répertoire actuel du script
script_directory = os.path.dirname(os.path.abspath(__file__))
# Construisez le chemin relatif vers le fichier CSV
film_affiche_csv = os.path.join(script_directory, '../_output/film_affiche.csv')

app = Flask(__name__)

# Charger le fichier CSV provenant de Talend
df_films = pd.read_csv(film_affiche_csv, sep=';')
# Convertir la colonne 'genres' en liste
df_films['genres'] = df_films['genres'].apply(json.loads)

# Fonction pour formater les noms des compagnies de production
def format_production_companies(production_companies):
    # Utilise une expression régulière pour extraire les éléments entre les guillemets
    elements = re.findall(r'"([^"]*)"', production_companies)
    # Joindre les éléments en une seule chaîne séparée par des virgules
    result = ','.join(elements)
    return result


@app.route('/')
def index():
    return render_template("index.html", films=df_films.to_dict(orient='records'))

@app.route('/details/<film_id>')
def details(film_id):
    film = df_films[df_films['id'] == int(film_id)].iloc[0].to_dict()
    # Formater les noms des compagnies de production
    film['production_companies'] = format_production_companies(film['production_companies'])
    return render_template("details.html", film=film)

if __name__ == '__main__':
    webbrowser.open("http://127.0.0.1:5000")
    app.run(debug=True)
