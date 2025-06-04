using Microsoft.EntityFrameworkCore;
using System;
using XSD.Models;

namespace XSD.Data
{
    public class ISSDbContext : DbContext
    {
        public ISSDbContext(DbContextOptions<ISSDbContext> options) : base(options) { }

        public DbSet<KeywordSuggestions> KeywordSuggestions { get; set; }
        public DbSet<Idea> Ideas { get; set; }
        public DbSet<Question> Questions { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<KeywordSuggestions>(entity =>
            {
                entity.ToTable("keyword_suggestions");

                entity.HasKey(k => k.Id);
                entity.Property(k => k.Id).HasColumnName("id");

                entity.HasMany(k => k.Ideas)
                      .WithOne(i => i.KeywordSuggestions)
                      .HasForeignKey(i => i.KeywordSuggestionsId)
                      .OnDelete(DeleteBehavior.Cascade);

                entity.HasMany(k => k.Questions)
                      .WithOne(q => q.KeywordSuggestions)
                      .HasForeignKey(q => q.KeywordSuggestionsId)
                      .OnDelete(DeleteBehavior.Cascade);
            });

            modelBuilder.Entity<Idea>(entity =>
            {
                entity.ToTable("ideas");

                entity.HasKey(i => i.Id);
                entity.Property(i => i.Id).HasColumnName("id");
                entity.Property(i => i.Keyword).HasColumnName("keyword");
                entity.Property(i => i.Difficulty).HasColumnName("difficulty");
                entity.Property(i => i.Volume).HasColumnName("volume");
                entity.Property(i => i.LastUpdated).HasColumnName("last_updated");
                entity.Property(i => i.KeywordSuggestionsId).HasColumnName("keyword_suggestions_id");
            });

            modelBuilder.Entity<Question>(entity =>
            {
                entity.ToTable("questions");

                entity.HasKey(q => q.Id);
                entity.Property(q => q.Id).HasColumnName("id");
                entity.Property(q => q.Keyword).HasColumnName("keyword");
                entity.Property(q => q.Difficulty).HasColumnName("difficulty");
                entity.Property(q => q.Volume).HasColumnName("volume");
                entity.Property(q => q.LastUpdated).HasColumnName("last_updated");
                entity.Property(q => q.KeywordSuggestionsId).HasColumnName("keyword_suggestions_id");
            });
        }
    }
}
